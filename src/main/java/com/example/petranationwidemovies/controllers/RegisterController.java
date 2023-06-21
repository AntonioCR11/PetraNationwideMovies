package com.example.petranationwidemovies.controllers;

import com.example.petranationwidemovies.Main;
import com.example.petranationwidemovies.model.User;
import com.example.petranationwidemovies.repositories.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField nrpField;
    @FXML
    public TextField phoneField;
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
        String phone = phoneField.getText();
        String password = passwordField.getText();

        User user = new User();
        user.setName(username);
        user.setNrp(nrp);
        user.setPhone(phone);
        user.setPassword(password);

        // todo set role
        user.setRole(1);
        if (isValidRegister(username,nrp,password)){
            errorMessageLabel.setText("Field Invalid!");
            return;
        }
        UserRepository userRepository = new UserRepository();
        try {
            userRepository.add(user);
            errorMessageLabel.setTextFill(Color.GREEN);
            errorMessageLabel.setText("Register berhasil, silahkan login!");

            nrpField.setText("");
            usernameField.setText("");
            passwordField.setText("");
            phoneField.setText("");

        } catch (SQLException e) {
            errorMessageLabel.setText("NRP sudah dipakai!");
        }

    }
    @FXML
    private void toLoginClicked() throws IOException {
        Stage stageTheLabelBelongs = (Stage) toLogin.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        stageTheLabelBelongs.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stageTheLabelBelongs.setScene(scene);
    }
    private boolean isValidRegister(String username, String nrp, String password) {
        return username.isEmpty() && password.isEmpty() && nrp.isEmpty();
    }
}
