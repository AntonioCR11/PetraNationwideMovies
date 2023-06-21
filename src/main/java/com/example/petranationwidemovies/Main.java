package com.example.petranationwidemovies;

import com.example.petranationwidemovies.database.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // open login window
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        stage.setResizable(false);

        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

        stage.setTitle("Petra Nationwide Movies");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//         DatabaseConnection.migrate();
        launch();
    }
}