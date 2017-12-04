/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class DBTableManager 
{
    private final Connection connection;
    
    public DBTableManager(Connection connect)
    {
        this.connection = connect;
    }
    
    public boolean containTable(String name)
    {
        DatabaseMetaData meta= getMeta();
        try {
            ResultSet res = meta.getTables(null, null, null,
                    new String[] {"TABLE"});
            
            while (res.next())
            {
                if(res.getString("TABLE_NAME").equalsIgnoreCase(name))
                    return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBTableManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<String> allTablesList()
    {
        DatabaseMetaData meta= getMeta();
        ArrayList<String> listTable = new ArrayList<>();
        try {
            ResultSet res = meta.getTables(null, null, null,
                    new String[] {"TABLE"});
            
            while (res.next())
            {
                listTable.add(res.getString("TABLE_NAME"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBTableManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTable;
    }
    
    public String allTablesString()
    {
        return Arrays.toString(allTablesList().toArray());
    }
    
    public void removeTable(String name)
    {
        executeUpdate("DROP TABLE " +name);
    }
    
    public void removeAllTables()
    {
        ArrayList<String> dbTables = allTablesList();
        dbTables.forEach((name) -> {
            removeTable(name);
        });
    }
    
    public void createTable(String tableName, String tableIDs)
    {
        String statement;
        statement = "CREATE TABLE " +tableName+ "(" +tableIDs+ ")";
        executeUpdate(statement);
    }
    
    public String createTableStatement(Class<? extends DBDataAbstract> returnType)
    {        
        DBDataAbstract data;
        
        try 
        {
            data = returnType.newInstance();
        } 
        catch (InstantiationException | IllegalAccessException e) 
        {
            System.err.println("Cannot create object of type " +returnType.getName());
            return null;
        }
        
        StringBuilder builder = new StringBuilder();
        {
            Iterator<Field> iterator = data.getIteratorFields();
            while(iterator.hasNext())
            {
                Field  field     = iterator.next();
                String fieldName = field.getName();
                if(iterator.hasNext())
                    builder.append(fieldName).append(" ").append(data.getDataType(field)).append(", ");
                else
                    builder.append(fieldName).append(" ").append(data.getDataType(field));
            }
        }
        return builder.toString();
    }
    
    private DatabaseMetaData getMeta()
    {
        try {
            return connection.getMetaData();
        } catch (SQLException ex) {
            Logger.getLogger(DBTableManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
    
    private void executeUpdate(String sqlStmt)
    {
        //Declare statement as null
        Statement stmt = null;
        try 
        {           
            //Create Statement
            stmt = connection.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } 
        catch (SQLException e) 
        {
            System.err.println(e);        
        }
    }
}
