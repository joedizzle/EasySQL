/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.fx.propertyeditor.editor;

import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.PropertyEditor;

/**
 *
 * @author user
 */
public class StringPropertyEditor implements PropertyEditor<StringProperty> {
    private final StringProperty stringData;
    
    public StringPropertyEditor(PropertySheet.Item item)
    {
        stringData = (StringProperty)item.getValue();        
    }

    @Override
    public Node getEditor() {
        TextField textField = new TextField(stringData.getValue());
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            stringData.setValue(newValue);
        });
        return textField;
    }

    @Override
    public StringProperty getValue() {
        return stringData;
    }

    @Override
    public void setValue(StringProperty t) {        
        stringData.setValue(t.getValue());
    }
}
