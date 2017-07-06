/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class DBAccess {    
    private Connection connection = null; 
    
    public DBAccess(String url, String user, String password)
    {
        try 
        {
            this.connection = DriverManager.getConnection(url, user, password);           
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isLoginSuccessful()
    {
        return connection != null;
    }
    
    public boolean isDisconnectSuccessful()
    {
        return connection == null;
    }
    
    public boolean disconnect()
    {
        try 
        {
            if(connection != null)
            {
                connection.close();
                connection = null;
            }
            else
            {                
                return false;
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void dbShutdown(String url)
    {
        try {
            DriverManager.getConnection(url);
        } catch (SQLException ex) {
            
        }
    }
    
    public DBQuery getQuery()
    {
        return new DBQuery(connection);
    }
        
    public ResultSet executeQuery(String queryStmt)
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
    
    public DBTableManager getTableManager()
    {
        return new DBTableManager(connection);
    }
    
    public DatabaseMetaData getMeta()
    {
        try {
            return connection.getMetaData();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   
    
    public DBMetaInfo getMetaInfo()
    {
        return new DBMetaInfo(connection);
    }
}
