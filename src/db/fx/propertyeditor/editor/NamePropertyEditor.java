/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.fx.propertyeditor.editor;

import java.util.Arrays;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.PropertyEditor;

/**
 *
 * @author user
 */
public class NamePropertyEditor implements PropertyEditor<StringProperty>{
    private final StringProperty name;
    private String[] list;
    
    public NamePropertyEditor(PropertySheet.Item item)
    {
        this.list = new String[]{""};
        name = (StringProperty)item.getValue();
    }
    
    public NamePropertyEditor(PropertySheet.Item item, String... list)
    {
        this.list = list;
        name = (StringProperty)item.getValue();
    }

    public void setList(String... list)
    {
        this.list = list;
    }
    
    @Override
    public Node getEditor() {
        ComboBox<String> combo = new ComboBox<>();
        ObservableList obsList = getObservableList();        
        combo.setItems(obsList);
        if(obsList.contains(name.getValue()))
            combo.setValue(name.getValue());   
        combo.valueProperty().addListener((observable, oldValue, newValue) -> {
            name.setValue(newValue);
        });
        return combo;
    }
    
    private ObservableList<String> getObservableList()
    {
        ObservableList obsList = FXCollections.observableArrayList();
        obsList.addAll(Arrays.asList(list));
        return obsList;
    }

    @Override
    public StringProperty getValue() {
        return name;
    }

    @Override
    public void setValue(StringProperty t) {
        this.name.setValue(t.getValue());
    }
}
