/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.dataeditor;

import db.fx.propertyeditor.DBPropertyDataEditor;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Test extends Application
{
    public static void main(String... args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DBPropertyDataEditor<Parameter> dialog = new DBPropertyDataEditor(new Parameter(), new ParameterEditorFactory());        
        Optional<Parameter> optional = dialog.showAndWait();
        
        if(optional.isPresent())
            System.out.println(optional.get().name);
        //SimpleParameter p = new SimpleParameter();
        //System.out.println(p.name);
    }
}
