/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.fx.propertyeditor;

import db.DBData;
import db.fx.propertyeditor.generic.AbstractDataEditorFactory;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import org.controlsfx.control.PropertySheet;

/**
 * FXML Controller class
 *
 * @author user
 * @param <T>
 */
public class DBPropertyDataEditor<T extends DBData> extends DialogPane
{

    /**
     * Initializes the controller class.
     */
    
    private final T dbdata;
    private final AbstractDataEditorFactory factory;
    
    @FXML
    PropertySheet propertySheet;
    
    public DBPropertyDataEditor(T dbdata, AbstractDataEditorFactory factory)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DBPropertyDataEditor.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } 
        this.dbdata = (T) dbdata.copy();   
        this.factory = factory;
        initialize();
    }
    
   
    private void initialize() 
    {      
        propertySheet.setPropertyEditorFactory(factory::call);
        propertySheet.setSearchBoxVisible(false);
        propertySheet.setModeSwitcherVisible(false);        
        initializePropertySheet();
    }
    
    private void initializePropertySheet()
    {
        propertySheet.getItems().addAll(factory.getItem(dbdata));        
    }
    
    public Optional<T> showAndWait()
    {
        Dialog dialog = new Dialog();
        dialog.setDialogPane(this);
        dialog.setTitle("Reactor design parameters");
        
        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == ButtonType.APPLY)
                return dbdata;
            return null;
        });
                
        return dialog.showAndWait();
    }
}
