package com.example.petranationwidemovies;

import com.example.petranationwidemovies.custom_ui.ListViewCell;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserController {
    @FXML
    private Button homeButton;
    @FXML
    private Button transactionButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button logoutButton;

    @FXML
    private ListView movieListView;
    private List<String> stringSet = new ArrayList<>(4);
    ObservableList observableList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        assert movieListView != null : "fx:id=\"movieListView\" was not injected: check your FXML file 'HomeView.fxml'.";
        setListView();
    }
    public void setListView()
    {
        Platform.runLater(() -> {
            stringSet.add("String 1");
            stringSet.add("String 2");
            stringSet.add("String 3");
            stringSet.add("String 4");
            observableList.setAll(stringSet);
            movieListView.setItems(observableList);
            movieListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("kontol");
                }
            });
//            movieListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>()
//            {
//                @Override
//                public ListCell<String> call(ListView<String> listView)
//                {
//                    return new ListViewCell();
//                }
//            });
        });
    }
    @FXML
    private void homeButtonClicked() throws IOException {
        Stage stageTheLabelBelongs = (Stage) logoutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomeView.fxml"));
        stageTheLabelBelongs.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stageTheLabelBelongs.setScene(scene);
    }
    @FXML
    private void transactionButtonClicked() throws IOException {
        Stage stageTheLabelBelongs = (Stage) logoutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("TransactionView.fxml"));
        stageTheLabelBelongs.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stageTheLabelBelongs.setScene(scene);
    }
    @FXML
    private void profileButtonClicked() throws IOException {
        Stage stageTheLabelBelongs = (Stage) logoutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ProfileView.fxml"));
        stageTheLabelBelongs.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stageTheLabelBelongs.setScene(scene);
    }
    @FXML
    private void logoutButtonClicked() throws IOException {
        Stage stageTheLabelBelongs = (Stage) logoutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        stageTheLabelBelongs.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stageTheLabelBelongs.setScene(scene);
    }
}
