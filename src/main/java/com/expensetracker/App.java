package com.expensetracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage firstStage) throws IOException {
        // calls FXMLLoader to get the specs for the UI defined in primary.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        //  defines the application window where UI elements are contained
        Scene scene = new Scene(root, 400, 300);

        // configures the main application window with the dimensions defined 
        firstStage.setTitle("Expense Tracker Start");
        firstStage.setScene(scene);
        firstStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
