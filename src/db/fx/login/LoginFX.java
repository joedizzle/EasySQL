/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.fx.login;

import db.DBAccess;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginFX extends DialogPane {
    
    @FXML
    TextField urlField;
    @FXML
    TextField userField;
    @FXML
    TextField passwordField;

    public LoginFX()
    {
        initDialogUI();
    }
    
    private void initDialogUI()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginFX.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }       
    }
    
    
    
    public Optional<DBAccess> showAndWait()
    {
        Dialog dialog = new Dialog();
        dialog.setDialogPane(this);
        dialog.setTitle("Reactor design parameters");
        
        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == ButtonType.APPLY)
                return null;
            return null;
        });
                
        return dialog.showAndWait();
    }
}
