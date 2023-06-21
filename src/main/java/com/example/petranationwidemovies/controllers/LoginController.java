package com.example.petranationwidemovies.controllers;

import com.example.petranationwidemovies.Main;
import com.example.petranationwidemovies.model.User;
import com.example.petranationwidemovies.model.UserLogin;
import com.example.petranationwidemovies.repositories.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginController {
    @FXML
    private TextField nrpField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Button loginBtn;
    public Hyperlink toRegister;
    @FXML
    private void loginButtonClicked() throws IOException {
        String nrp = nrpField.getText();
        String password = passwordField.getText();

        UserRepository userRepository = new UserRepository();
        try {
            List<User> user = (List<User>) userRepository.getByNrpAndPassword(nrp,password);

            if(user.isEmpty()){
                errorMessageLabel.setText("Invalid nrp atau password");
                return;
            }
            UserLogin.setUser(user.get(0));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Stage stageTheLabelBelongs = (Stage) toRegister.getScene().getWindow();
        String route = "";
        if(UserLogin.getUser().getRole() == 0){
            route = "AdminMovieView.fxml";
        }else{
            route = "HomeView.fxml";
        }
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(route));
        stageTheLabelBelongs.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stageTheLabelBelongs.setScene(scene);
    }
    @FXML
    private void toRegisterClicked() throws IOException {
        Stage stageTheLabelBelongs = (Stage) toRegister.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RegisterView.fxml"));
        stageTheLabelBelongs.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stageTheLabelBelongs.setScene(scene);
    }
    private boolean isValidLogin(String nrp, String password){
        try {
            UserRepository userRepository = new UserRepository();
            User user = (User) userRepository.getByNrpAndPassword(nrp,password);
            if(user == null){
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
