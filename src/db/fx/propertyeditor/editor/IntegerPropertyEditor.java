/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.fx.propertyeditor.editor;

import java.util.function.UnaryOperator;
import javafx.beans.property.IntegerProperty;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.PropertyEditor;

/**
 *
 * @author user
 */
public class IntegerPropertyEditor implements PropertyEditor<IntegerProperty>{
    private final IntegerProperty data;
    
    public IntegerPropertyEditor(PropertySheet.Item item)
    {
        data = (IntegerProperty)item.getValue();
    }

    @Override
    public Node getEditor() {
        TextFormatter textFormatter = new TextFormatter(new IntegerStringConverter(), data.getValue(), getFilter());   
        TextField textField = new TextField();
        textField.setTextFormatter(textFormatter);
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            data.setValue(Integer.parseInt(newValue));
        });
        return textField;
    }

    @Override
    public IntegerProperty getValue() {
        return data;
    }

    @Override
    public void setValue(IntegerProperty t) {
        this.data.setValue(t.getValue());
    }
    
    private UnaryOperator<TextFormatter.Change> getFilter() {
        return change -> {
            String string = change.getText();      
            boolean isNumber = string.matches("-?\\d+(\\.\\d+)?");
            
            if(!change.isAdded())            
                return change;
            if(change.isAdded())
                if(isNumber)
                    return change;            
            return null;
         };
    }
}
