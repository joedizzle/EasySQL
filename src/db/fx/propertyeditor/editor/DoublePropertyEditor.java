/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.fx.propertyeditor.editor;

import java.util.function.UnaryOperator;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.PropertyEditor;

/**
 *
 * @author user
 */
public class DoublePropertyEditor implements PropertyEditor<DoubleProperty> {

    private final DoubleProperty number;
    
    public DoublePropertyEditor(PropertySheet.Item item)
    {
        number = (DoubleProperty)item.getValue();
    }
    
    @Override
    public Node getEditor() {
        TextFormatter textFormatter = new TextFormatter(new DoubleStringConverter(), number.getValue(), getFilter());   
        TextField textField = new TextField();
        textField.setTextFormatter(textFormatter);
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            number.setValue(Double.parseDouble(newValue));
        });
        return textField;
    }

    @Override
    public DoubleProperty getValue() {
        return number;
    }

    @Override
    public void setValue(DoubleProperty t) {
        this.number.setValue(t.getValue());
    }
    
    private UnaryOperator<TextFormatter.Change> getFilter() {
        return change -> {
            String string = change.getText();      
            boolean isNumber = string.matches("-?\\d+(\\.\\d+)?");
            
            if(!change.isAdded())            
                return change;
            if(change.isAdded())
                if(string.contains(".") || isNumber)
                    return change;            
            return null;
         };
    }
}
