/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class TestProgressIndicator extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Progress Indicator Dialog");
        StackPane root = new StackPane();
        Button btn = new Button();
        btn.setText("Launch Progress Indicator");
        btn.setOnAction((ActionEvent event) -> {
            root.getChildren().add(new ProgressIndicator());
        });
        
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
    
}
