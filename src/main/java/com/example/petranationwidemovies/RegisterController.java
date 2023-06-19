package com.example.petranationwidemovies;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField nrpField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Button registerBtn;
    public Hyperlink toLogin;
    @FXML
    private void registerButtonClicked() throws IOException {
        String nrp = nrpField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isValidLogin(username, nrp, password)) {

        } else {
            errorMessageLabel.setText("Invalid fields!");
        }
    }
    @FXML
    private void toLoginClicked() throws IOException {
        Stage stageTheLabelBelongs = (Stage) toLogin.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        stageTheLabelBelongs.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stageTheLabelBelongs.setScene(scene);
    }
    private boolean isValidLogin(String username, String nrp, String password) {

        return username.equals("admin") && password.equals("password");
    }
}
