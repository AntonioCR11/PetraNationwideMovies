package com.example.petranationwidemovies;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Button loginBtn;
    public Hyperlink toRegister;
    @FXML
    private void loginButtonClicked() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();


        if (isValidLogin(username, password)) {
            Stage stageTheLabelBelongs = (Stage) toRegister.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomeView.fxml"));
            stageTheLabelBelongs.setResizable(false);
            Scene scene = new Scene(fxmlLoader.load(), 720, 480);
            stageTheLabelBelongs.setScene(scene);
        } else {
            errorMessageLabel.setText("Invalid nrp or password");
        }
    }
    @FXML
    private void toRegisterClicked() throws IOException {
        Stage stageTheLabelBelongs = (Stage) toRegister.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RegisterView.fxml"));
        stageTheLabelBelongs.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stageTheLabelBelongs.setScene(scene);
    }
    private boolean isValidLogin(String username, String password) {
        return username.equals("admin") && password.equals("123");
    }
}
