package ru.specialist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = FXMLLoader.load(getClass().getResource("Views/BrowserFX.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("Browser JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}
