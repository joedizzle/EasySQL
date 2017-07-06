/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class DBMetaInfo
{       
    private Connection connection = null;
    
    public DBMetaInfo(Connection connection)
    {                
        this.connection = connection;
    }
    
    public ArrayList<String> listTable()
    {        
        DatabaseMetaData metadata = getMeta();
        ArrayList<String> listTable = new ArrayList<>();
        try {
            ResultSet res = metadata.getTables(null, null, null,
                    new String[] {"TABLE"});
            
            while (res.next())
            {
                listTable.add(res.getString("TABLE_NAME"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBMetaInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTable;
    }
    
    public ArrayList<String> getAllRowData(String table)
    {
        ResultSet rs = executeQuery("select * from " +table);
        ArrayList<String> listRow = new ArrayList<>();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();                        
            while(rs.next())
            {
                String string = "";
                for(int i = 1; i<=columnsNumber; i++)
                {
                    string += rs.getString(i)+ " ";
                }
                listRow.add(string);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBMetaInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRow;
    }
    
    public ArrayList<String> getTableColumns(String table)
    {        
        DatabaseMetaData metadata = getMeta();
        ArrayList<String> listColumn = new ArrayList<>();
        try {
            ResultSet res = metadata.getColumns(null, null, table, null);
            while(res.next())
            {
                listColumn.add(res.getString(4));
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DBMetaInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listColumn;
    }
    
    public boolean containTable(String name)
    {       
        DatabaseMetaData metadata = getMeta();
        try {
            ResultSet res = metadata.getTables(null, null, null,
                    new String[] {"TABLE"});
            
            while (res.next())
            {
                if(res.getString("TABLE_NAME").equalsIgnoreCase(name))
                    return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBMetaInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int getColumnCount(String table)
    {
        ResultSet rs = executeQuery("SELECT * FROM " +table);
        ResultSetMetaData rsmd;
        try {
            rsmd = rs.getMetaData();
            return rsmd.getColumnCount();
        } catch (SQLException ex) {
            Logger.getLogger(DBMetaInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public String getColumnTypeName(String table, int column)
    {
        ResultSet rs = executeQuery("SELECT * FROM " +table);
        ResultSetMetaData rsmd;
        try {
            rsmd = rs.getMetaData();
            return rsmd.getColumnTypeName(column);
        } catch (SQLException ex) {
            Logger.getLogger(DBMetaInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getColumnName(String table, int column)
    {
        ResultSet rs = executeQuery("SELECT * FROM " +table);
        ResultSetMetaData rsmd;
        try {
            rsmd = rs.getMetaData();
            return rsmd.getColumnName(column);
        } catch (SQLException ex) {
            Logger.getLogger(DBMetaInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private DatabaseMetaData getMeta()
    {
        try {
            return connection.getMetaData();
        } catch (SQLException ex) {
            Logger.getLogger(DBMetaInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
    
    public void printColumnInfo(String table)
    {        
        int columnCount = getColumnCount(table);
        
        for(int i = 1; i<=columnCount; i++)
            System.out.printf("%-10s %-4s \n", getColumnTypeName(table, i), getColumnName(table, i));
    }
}
