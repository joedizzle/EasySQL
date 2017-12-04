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
public class DerbyField {
    
    private Object value = null;
    
    public DerbyField(Object value)
    {
        this.value = value;
    }
       
    public DerbyCondition equal(DerbyField field)
    {
        return new DerbyCondition(value+ "=" +field.getDerbyValue()+ " ");        
    }
    
    public Object getValue()
    {
        return value;
    }
            
    public String getDerbyValue()
    {
        if(getValue() instanceof String)
            return "'" +getValue()+ "'";
        else if(getValue() instanceof Number)
            return String.valueOf(value);
        else
            return null;
    }
    
    public static String getDerbyValues(DBData data)
    {
        Object[] values = data.getValues();
        String string = "";
        for(int i = 0; i<values.length-1; i++)
            string += new DerbyField(values[i]).getDerbyValue()+ ", ";
        string += new DerbyField(values[values.length-1]).getDerbyValue();
        return string;
    }
    
    public static String getDerbyAllFieldAndValue(DBData data)
    {
        String[] fieldNames  = data.getFieldNames();
        Object[] fieldValues = data.getValues();
        
        int size = fieldNames.length;
        String string = "";
        
        for(int i = 0; i<size-1; i++)
            string += fieldNames[i]+ "=" +new DerbyField(fieldValues[i]).getDerbyValue()+ ", ";
        string += fieldNames[size-1]+ "=" +new DerbyField(fieldValues[size-1]).getDerbyValue();
        
        return string;
    }
}
