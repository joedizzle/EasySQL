/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import db.DBStatement;

/**
 *
 * @author user
 */
public class SQLStatement extends DBStatement {
    private String string = null;
    
    public SQLStatement()
    {
        
    }
    
    @Override
    public String toString()
    {
        return string;
    }
    
    public SQLStatement selectFrom(String table)
    {
        this.string = "SELECT * FROM " +table+ " ";
        return this;
    }
    
    public SQLStatement where(String column, String operator, String value)
    {
        this.string += "WHERE " +column+operator+ " '" +value+ "' ";
        return this;
    }
    
    public SQLStatement where(String column, String operator, int value)
    {
        this.string += "WHERE " +column+operator+value+ " ";
        return this;
    }
    
    public SQLStatement where(String column, String operator, float value)
    {
        this.string += "WHERE " +column+operator+value+ " ";
        return this;
    }
    
    public SQLStatement and(String column, String operator, String value)
    {
        this.string += "AND " +column+operator+ " '" +value+ "' ";
        return this;
    }
    
    public SQLStatement and(String column, String operator, int value)
    {
        this.string += "AND " +column+operator+value+ " ";
        return this;
    }
    
    public SQLStatement and(String column, String operator, float value)
    {
        this.string += "AND " +column+operator+value+ " ";
        return this;
    }
    
    public SQLStatement or(String column, String operator, String value)
    {
        this.string += "OR " +column+operator+ " '" +value+ "' ";
        return this;
    }
    
    public SQLStatement or(String column, String operator, int value)
    {
        this.string += "OR " +column+operator+value+ " ";
        return this;
    }
    
    public SQLStatement or(String column, String operator, float value)
    {
        this.string += "OR " +column+operator+value+ " ";
        return this;
    }
}
