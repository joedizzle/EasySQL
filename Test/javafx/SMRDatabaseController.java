/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import db.DBAccess;
import db.DBQuery;
import db.dao.SQLStatement;
import db.fx.UtilityFX;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SMRDatabaseController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @FXML
    TableView databaseView;
    @FXML
    TextField filterCountries;
    
    @FXML
    HBox searchHBox;
    @FXML
    VBox filterVBox;
    
    DBAccess database;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // init variables        
        database = new DBAccess("jdbc:derby:C:\\Users\\user\\Desktop\\Reactor Database\\smr\\data;create=true", "tom", "secret");
        System.out.println("Is connection successfull   : " +database.isLoginSuccessful());
        
        SQLStatement parameter = new SQLStatement();
        parameter.selectFrom("SMRDATABASE");
        
        //where
        parameter.where("origin", "=", "China")
                .or("origin", "=", "USA")
                .or("origin", "=", "United States of America");
              
        DBQuery query = database.getQuery().readTable(parameter);
        ArrayList<Parameter> allData = query.getAllRows(Parameter.class);
        
        UtilityFX.initTableColumn(new Parameter(), databaseView);
        initTable(allData);        
         
    }    
       
    public void close(ActionEvent event)
    {        
        database.disconnect();
        System.out.println("Is disconnection successfull: " +database.isDisconnectSuccessful());
        System.exit(0);
    }
      
    public void initTable(ArrayList<Parameter> parameterList)
    {   
        ObservableList<Parameter> data = FXCollections.observableArrayList(parameterList);
        FilteredList<Parameter> filteredData = new FilteredList<>(data, p-> true);
        
        filterCountries.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(parameter -> {
                if(newValue == null || newValue.isEmpty())
                    return true;
                
                String lowerCaseFilter = newValue.toLowerCase();
                return parameter.origin.get().toLowerCase().contains(lowerCaseFilter);
            });
        });
        
        SortedList<Parameter> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(databaseView.comparatorProperty());
        
        databaseView.setItems(sortedData);        
    }
}
