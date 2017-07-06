/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;

/**
 *
 * @author user
 */
public class DBDataMap 
{
    public static boolean isWritableValue(Field field, Object wrapper)
    {
        try 
        {
            return field.get(wrapper) instanceof WritableValue;
        } 
        catch (IllegalArgumentException | IllegalAccessException ex) 
        {
            Logger.getLogger(DBDataMap.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }        
    }
    public static WritableValue getWritableValue(Field field, Object wrapper)
    {
        if(isWritableValue(field, wrapper))
            try {
                return (WritableValue)field.get(wrapper);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(DBDataMap.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        else
            return null;
    }
        
    public static WritableValue getWritableValueIgnoreCase(String fieldName, Field[] fields, Object wrapper)
    {
        Field field = getFieldIgnoreCase(fieldName, fields);
        
        if(field != null && isWritableValue(field, wrapper))
            return getWritableValue(field, wrapper);
        else
            return null;
    }
    
    public static boolean isObservableValue(Field field, Object wrapper)
    {
        try 
        {
            return field.get(wrapper) instanceof ObservableValue;
        } 
        catch (IllegalArgumentException | IllegalAccessException ex) 
        {
            Logger.getLogger(DBDataMap.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }        
    }
    
    public static ObservableValue getObservableValue(Field field, Object wrapper)
    {
        if(isObservableValue(field, wrapper))
            try {
                return (ObservableValue)field.get(wrapper);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(DBDataMap.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        else
            return null;
    }
    
    public static ObservableValue getObservableValueIgnoreCase(String fieldName, Field[] fields, Object wrapper)
    {
        Field field = getFieldIgnoreCase(fieldName, fields);
        
        if(field != null && isObservableValue(field, wrapper))
            return getObservableValue(field, wrapper);
        else
            return null;
    }
    
    public static Field getFieldIgnoreCase(String fieldName, Field[] fields)
    {          
        for(Field field : fields)
            if(fieldName.equalsIgnoreCase(field.getName()))
                return field;
        return null;
    }
    
    public static HashMap<String, WritableValue> getDataMap(DBData data)
    {
        HashMap<String, WritableValue> map = new HashMap<>();
        Field[] fields = data.getFields();
        
        for(Field field : fields)
        {
            String name = field.getName();
            WritableValue value = getWritableValue(field, data);
            map.put(name, value);
        }
        return map;
    }
    
    public static void writeData(ResultSet rowData, DBData t)
    {
        try 
        {
            ResultSetMetaData rsmd = rowData.getMetaData();
            int columnCount = rsmd.getColumnCount();
            
            for(int i = 1; i<=columnCount; i++)
            {
                WritableValue value = getWritableValueIgnoreCase(rsmd.getColumnName(i), t.getFields(), t);  
                if(value != null)
                    value.setValue(rowData.getObject(i));                
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
}
