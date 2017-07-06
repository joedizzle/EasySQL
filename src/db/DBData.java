/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;

/**
 *
 * @author user
 * @param <T>
 */
public abstract class DBData<T extends DBData>
{
    public ObservableValue getObservableValue(Field field)
    {
        return DBDataMap.getObservableValue(field, this);
    }
    
    public WritableValue getWritableValue(Field field)
    {
        return DBDataMap.getWritableValue(field, this);
    }
    
    public ObservableValue getObservableValueIgnoreCase(String fieldName)
    {        
        return DBDataMap.getObservableValueIgnoreCase(fieldName, getFields(), this);
    }
    
    public WritableValue getWritableValueIgnoreCase(String fieldName)
    {        
        return DBDataMap.getWritableValueIgnoreCase(fieldName, getFields(), this);
    }
            
    public Field[] getFields()
    {
        return this.getClass().getFields();
    }
    
    public WritableValue[] getAllWritableValues()
    {
        Field[] fields = getFields();
        WritableValue[] values = new WritableValue[fields.length];
        
        for(int i = 0; i<fields.length; i++)
            values[i] = getWritableValue(fields[i]);
        
        return values;
    }
    
    public T copy()
    {
        T t = instantiate();
        
        WritableValue[] writables1 = getAllWritableValues();
        WritableValue[] writables2 = t.getAllWritableValues();
        for(int i = 0; i<writables1.length; i++)
            writables2[i].setValue(writables1[i].getValue());
        
        return t;
    }
    
    public void set(T t)
    {
        if(!DBData.class.isInstance(t)) return;
        
        WritableValue[] writables1 = getAllWritableValues();
        WritableValue[] writables2 = t.getAllWritableValues();
        
        for(int i = 0; i<writables1.length; i++)
            writables1[i].setValue(writables2[i].getValue());
    }
    
    private T instantiate()
    {
        T t = null;
        
        try {
            t = (T) this.getClass().newInstance();
            return t;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DBData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    
    public String[] getFieldNames()
    {
        Field[] fields = getFields();
        String[] fieldNames = new String[fields.length];
        
        for(int i = 0; i<fields.length; i++)
            if(DBDataMap.isWritableValue(fields[i], this))
                fieldNames[i] = fields[i].getName();
        return fieldNames;
    }
        
    public ArrayList<String> getFieldNameList()
    {
        Field[] fields = getFields();
        ArrayList<String> fieldNameList = new ArrayList<>();        
        for(Field field : fields)
        {
            if(DBDataMap.isWritableValue(field, this))
                fieldNameList.add(field.getName());
        }
        return fieldNameList;
    }       
}
