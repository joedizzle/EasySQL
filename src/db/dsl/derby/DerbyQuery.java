/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dsl.derby;

import db.DBData;

/**
 *
 * @author user
 */
public class DerbyQuery {
    private String string = null;
    
    public DerbyQuery(String string)
    {
        this.string = string;
    }
    
    public DerbyQuery selectAll()
    {
        return new DerbyQuery(string+ "SELECT * ");
    }
        
    public DerbyQuery from(String table)
    {
        return new DerbyQuery(string+ "FROM " +table+ " ");
    }
    
    public DerbyQuery update(String table)
    {
        return new DerbyQuery(string+ "UPDATE " +table+ " ");
    }
    
    public DerbyQuery set(DBData data)
    {
        return new DerbyQuery(string+ "SET " +DerbyField.getDerbyAllFieldAndValue(data)+ " ");        
    }
    
    public DerbyQuery insertInto(String table, DBData data)
    {
        return new DerbyQuery(string+ "INSERT INTO " +table+ " VALUES (" +DerbyField.getDerbyValues(data)+ ")");
    }
    
    public DerbyQuery deleteFrom(String table)
    {
        return new DerbyQuery(string+ "DELETE FROM " +table+ " ");
    }
    
    public DerbyQuery where(DerbyCondition condition)
    {
        return new DerbyQuery(string+ "WHERE " +condition.getSQLStatement()+ " ");
    }
    
    public DerbyQuery whereTrue()
    {
        return new DerbyQuery(string+ "WHERE " +"TRUE"+ " ");
    }
    
    public DerbyQuery whereFalse()
    {
        return new DerbyQuery(string+ "WHERE " +"FALSE"+ " ");
    }
    
    public DerbyQuery whereIn(String column, String... values)
    {        
        String newString = "WHERE "+column+ " IN (" ;
        
        int i = 1;
        for (String value : values) {
            if (i++ == values.length) {
                // end
                newString += "'" +value+ "')";
                break;
            }
            newString += "'" +value+ "', ";
        }
        
        return new DerbyQuery(string+newString);
    }
    
    public DerbyQuery whereNotIn(String column, String... values)
    {
        String newString = "WHERE "+column+ " NOT IN (" ;
        
        int i = 1;
        for (String value : values) {
            if (i++ == values.length) {
                // end
                newString += "'" +value+ "')";
                break;
            }
            newString += "'" +value+ "', ";
        }
        
        return new DerbyQuery(string+newString);
    }
        
    public DerbyQuery whereNot(DerbyCondition condition)
    {
        return new DerbyQuery(string+ "WHERE NOT " +condition.getSQLStatement()+ " ");
    }
           
    public DerbyQuery and(DerbyCondition condition)
    {
        return new DerbyQuery(string+ "AND " +condition.getSQLStatement()+ " ");
    }
    
    public DerbyQuery or(DerbyCondition condition)
    {
        return new DerbyQuery(string+ "OR " +condition.getSQLStatement()+ " ");
    }
    
    public String getSQLStatement()
    {
        return string.trim();
    }
}
