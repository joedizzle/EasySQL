/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.fx.propertyeditor;

import java.util.Optional;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import org.controlsfx.control.PropertySheet;

/**
 *
 * @author user
 */
public class DBDataItem  implements PropertySheet.Item
{
    private final String name;
    private final ObjectProperty valueObj;

    public DBDataItem(String name, Object object)
    {
        this.name = name;
        this.valueObj = new SimpleObjectProperty(object);
    }
    @Override
    public Class<?> getType() {
        return valueObj.getValue().getClass();
    }

    @Override
    public String getCategory() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Object getValue() {
        return valueObj.getValue();
    }

    @Override
    public void setValue(Object o) {
        this.valueObj.setValue(o);
    }

    @Override
    public Optional<ObservableValue<? extends Object>> getObservableValue() {   
        return Optional.of(valueObj);
    }    
    
    @Override
    public String toString()
    {
        return String.format("%-45s %s", name, valueObj.get().toString()); 
    }
}
