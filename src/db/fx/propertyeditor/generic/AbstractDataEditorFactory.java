/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.fx.propertyeditor.generic;



import db.DBData;
import db.fx.propertyeditor.DBDataItem;
import db.fx.propertyeditor.editor.DoublePropertyEditor;
import db.fx.propertyeditor.editor.IntegerPropertyEditor;
import db.fx.propertyeditor.editor.StringPropertyEditor;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.WritableValue;
import org.controlsfx.control.PropertySheet.Item;
import org.controlsfx.property.editor.PropertyEditor;

/**
 *
 * @author user
 * @param <T>
 */
public abstract class AbstractDataEditorFactory <T extends DBData> 
{
    public abstract PropertyEditor callCustom(Item item);
    
    public PropertyEditor call(Item item)
    {
        PropertyEditor editor = callCustom(item);
        
        if(editor != null)
            return editor;
        
        if(item.getValue() instanceof StringProperty)
            return new StringPropertyEditor(item);
        else if(item.getValue() instanceof DoubleProperty)
            return new DoublePropertyEditor(item);
        else if(item.getValue() instanceof IntegerProperty)
            return new IntegerPropertyEditor(item);         
        else
            return new StringPropertyEditor(item);
    }
    
    public ArrayList<DBDataItem> getItem(T t)
    {
        ArrayList<DBDataItem> items = new ArrayList<>();
        
        WritableValue[] values = t.getAllWritableValues();
        String[] names = t.getFieldNames();
        
        for(int i = 0; i<names.length; i++)            
        {
            items.add(new DBDataItem(names[i], values[i]));
        }
        
        return items;
    }
    
}
