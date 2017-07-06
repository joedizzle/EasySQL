/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import db.DBData;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;

/**
 *
 * @author user
 */
public abstract class DBDataAbstract 
{
    public abstract void setResultData(ResultSet rowData);  
    public abstract String getInsertRowStatement(String table);
    public abstract String getUpdateRowStatement(String table, String name, DBDataAbstract value);
    public abstract String getDeleteDataStatement(String table);
    public abstract String getDataType(Field field);
    
    public ObservableValue getObservableValue(Field field)
    {
        try 
        {
            return (ObservableValue)field.get(this);
        } 
        catch (IllegalArgumentException | IllegalAccessException ex) 
        {
            Logger.getLogger(DBData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public WritableValue getWritableValue(Field field)
    {
        try 
        {
            return (WritableValue)field.get(this);
        } 
        catch (IllegalArgumentException | IllegalAccessException ex) 
        {
            Logger.getLogger(DBData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    protected Iterator<Field> getIteratorFields()
    {
         return Arrays.asList(getFields()).iterator();         
    }
    
    public Field[] getFields()
    {
        return this.getClass().getFields();
    }
    
    public ArrayList<String> getFieldNameList()
    {
        Field[] fields = getFields();
        ArrayList<String> fieldNameList = new ArrayList<>();        
        for(Field field : fields)
            fieldNameList.add(getFieldName(field));
        return fieldNameList;
    }
    
    public boolean hasFieldIgnoreCase(String fieldName)
    {
        Field[] fields = getFields();        
        for(Field field : fields)
            if(fieldName.equalsIgnoreCase(getFieldName(field)))
                return true;
        return false;
    }
    
    public WritableValue getWritableValueIgnoreCase(String fieldName)
    {
        Field[] fields = getFields();        
        for(Field field : fields)
            if(fieldName.equalsIgnoreCase(getFieldName(field)))
                return getWritableValue(field);
        return null;
    }
    
    public String getFirstFieldName()
    {
        return getFields()[0].getName();
    }
    
    public Object getFirstFieldValue()
    {
        return getObservableValue(getFields()[0]).getValue();
    }
    
    public boolean isFirstValueString()
    {
        return (getFirstFieldValue() instanceof String);
    }
    
    protected int getColumnSize()
    {
        return getFields().length;
    }
    
    public boolean isString(Field field)
    {
        return (getObservableValue(field) instanceof StringProperty);
    }
    
    public String getFieldName(Field field)
    {       
        return field.getName();
    }
    
    public Object getFieldValue(Field field)
    {
        return getObservableValue(field).getValue();
    }
}
