/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
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
public class DBConnection 
{        
    private Statement stmt;
    private Connection connection;
   
    public DBConnection(Connection connection)
    {        
        this.connection = connection;
        try {
            this.stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DBConnection(String url, String user, String password)
    {
        try 
        {
            this.connection = DriverManager.getConnection(url, user, password);        
            this.stmt = connection.createStatement();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void executeUpdate(String statement)
    {
        try 
        {
           stmt.executeUpdate(statement);            
        } catch (SQLException ex) {
           Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public <T extends DBData> ArrayList<T> executeAndFetch(String statement, Class<T> clazz)
    {
        try {
            ResultSet rs = stmt.executeQuery(statement); 
            ArrayList<T> rows = new ArrayList<>();
            while(rs.next())
            {
                rows.add(getRow(rs, clazz));
            }
            return rows;
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private <T extends DBData> T getRow(ResultSet rs, Class<T> returnType) throws InstantiationException, IllegalAccessException
    {               
        T t = returnType.newInstance();
        DBDataMap.writeData(rs, t);
        return t;
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
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void shutdown(String url)
    {
        try {
            DriverManager.getConnection(url);
        } catch (SQLException ex) {
            
        }
    }
}
