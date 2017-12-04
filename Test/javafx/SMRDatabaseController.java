/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import javafx.data.Parameter;
import javafx.data.Reactor;
import db.DBConnection;
import static db.dsl.derby.DerbyFactory.create;
import static db.dsl.derby.DerbyFactory.field;
import db.dsl.derby.DerbyQuery;
import db.fx.UtilityFX;
import db.fx.propertyeditor.DBPropertyDataEditor;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static javafx.SMRQueryFactory.getAll;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    TableView<Parameter> databaseView;  
    @FXML
    TableView<Reactor> reactorView;
    
    @FXML
    TextField filterText;    
    @FXML
    ToggleGroup filterToggle;
        
    @FXML
    HBox searchHBox;
    @FXML
    VBox filterVBox;
    
    @FXML
    ToggleGroup typeToggle;
    @FXML
    ToggleGroup countryToggle;
        
    ObservableList<Parameter> allData;
    ObservableList<Reactor> reactorData;
    
    String filterString = "Country";
    DBConnection connection;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //Initialize table column      
        UtilityFX.createColumns(new Parameter(), databaseView);   
        UtilityFX.createColumns(new Reactor(), reactorView);
        
        //Establish database connection
        connection = new DBConnection("jdbc:derby:C:\\Users\\user\\Desktop\\Reactor Database\\smr\\data;create=true", "tom", "secret");
        
        initTable(getAll());    
        
        typeToggle.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            RadioButton chk = (RadioButton)new_toggle.getToggleGroup().getSelectedToggle();                
            initTable(SMRQueryFactory.getType(chk.getText()).getSQLStatement());
        });        
        countryToggle.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> 
        {
            RadioButton chk = (RadioButton)new_toggle.getToggleGroup().getSelectedToggle();                
            initTable(SMRQueryFactory.getCountry(chk.getText()).getSQLStatement());
        });        
         
    }    
    
    public void editReactor(ActionEvent event)
    {
        Parameter parameter = databaseView.getSelectionModel().getSelectedItem();        
        
        if(parameter != null) 
        {
            DBPropertyDataEditor<Parameter> dialog = new DBPropertyDataEditor(parameter, new javafx.dataeditor.ParameterEditorFactory());        
            Optional<Parameter> optional = dialog.showAndWait();
            if(optional.isPresent())                
            {
                DerbyQuery query = create()
                    .update("SMRDATABASE")
                    .set(optional.get())
                    .where(field("name").equal(field(parameter.name.get())));
                connection.executeUpdate(query.getSQLStatement());  
                allData.set(allData.indexOf(parameter), optional.get());
            }
        }
    }
    
    public void addReactor(ActionEvent event)
    {
        DBPropertyDataEditor<Parameter> dialog = new DBPropertyDataEditor(new Parameter(), new javafx.dataeditor.ParameterEditorFactory());        
        Optional<Parameter> optional = dialog.showAndWait();
        if(optional.isPresent())
        {
            Parameter parameter = optional.get();
            if(parameter.name.get() == null || parameter.name.get().equals("")) return;
                          
            DerbyQuery query = create()
                    .insertInto("SMRDATABASE", parameter);
                        
            connection.executeUpdate(query.getSQLStatement());      
            allData.add(parameter);
            
            databaseView.scrollTo(parameter);
            databaseView.getSelectionModel().select(parameter);
        }
    }
    
    public void deleteReactor(ActionEvent event)
    {
        int selectedIndex = databaseView.getSelectionModel().getSelectedIndex();
        Parameter parameter = databaseView.getItems().get(selectedIndex);
        if(parameter == null) return;
        
        DerbyQuery query = create()
                .deleteFrom("SMRDATABASE")
                .where(field("name").equal(field(parameter.name.get())));
        
        connection.executeUpdate(query.getSQLStatement());
        allData.remove(parameter);
    }
       
    public void close(ActionEvent event)
    {        
        connection.disconnect();        
        System.exit(0);
    }
    
    public void initTable(String query)
    {           
        //Over view column
        allData = UtilityFX.getObservableArrayList(connection.executeAndFetch(query, Parameter.class));         
        databaseView.setItems(allData);     
        
        reactorData = UtilityFX.getObservableArrayList(connection.executeAndFetch(query, Reactor.class));
        reactorView.setItems(reactorData);
    }      
}
