/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class DBQuery 
{
    private final Connection connection;    
    private ResultSet rs;
    
    public DBQuery(Connection connection)
    {
        this.connection = connection;      
    }
    
    public DBQuery readTable(DBStatement statement)
    {
        DBQuery query = new DBQuery(connection);
        query.rs = executeQuery(statement.toString());
        return query;
    }
        
    public boolean next()
    {
        try {
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean previous()
    {
        try {
            return rs.previous();
        } catch (SQLException ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean first()
    {
        try {
            return rs.first();
        } catch (SQLException ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public <T extends DBData> T getNextRow(Class<T> returnType)
    {
        if(next())
            return getRow(returnType);
        else
            return null;
    }
    
    public <T extends DBData> ArrayList<T> getAllRows(Class<T> returnType)
    {
        ArrayList<T> rows = new ArrayList<>();
        while(next())
        {
            rows.add(getRow(returnType));
        }
        return rows;
    }
        
    public <T extends DBData> T getRow(Class<T> returnType)
    {
        
        try 
        {
            T t = returnType.newInstance();
            DBDataMap.writeData(rs, t);
            return t;
        } 
        catch (InstantiationException | IllegalAccessException ex) 
        {
            System.err.println("Cannot create object of type " +returnType.getName());
            return null;
        }
    }
           
    private ResultSet executeQuery(String queryStmt)
    {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        
        try {
            
            //Create statement
            stmt = connection.createStatement();
 
            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);
        }
        catch (SQLException e)
        {
            System.err.println(e);           
        }
        
        return resultSet;
    }
}
