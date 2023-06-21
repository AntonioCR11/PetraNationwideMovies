package com.example.petranationwidemovies.controllers;

import com.example.petranationwidemovies.Main;
import com.example.petranationwidemovies.model.*;
import com.example.petranationwidemovies.repositories.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.UnaryOperator;

public class AdminController implements Initializable {
    // Variable navigasi
    @FXML
    private Button homeButton;
    @FXML
    private Button transactionButton;
    @FXML
    private Button customerButton;
    @FXML
    public Button recruitButton;
    @FXML
    private Button logoutButton;

    // Variable untuk home
    @FXML
    TableView movieTableView = new TableView();
    ObservableList<Map<String, Object>> movieItems = FXCollections.<Map<String, Object>>observableArrayList();
    @FXML
    public Button addNewMovieButton;

    // Variable untuk transaction
    @FXML
    TableView transactionTableView = new TableView();
    ObservableList<Map<String, Object>> transactionItems = FXCollections.<Map<String, Object>>observableArrayList();

    // Variable movie popup
    @FXML
    public Label errorMessageLabel;
    @FXML
    public StackPane imageContainer = new StackPane();
    @FXML
    public ImageView movieImageView = new ImageView();
    @FXML
    public TextField movieIdField = new TextField();
    @FXML
    public TextField movieNameField = new TextField();
    @FXML
    public TextField imageTextField;
    @FXML
    public DatePicker startDateField = new DatePicker();
    @FXML
    public DatePicker endDateField = new DatePicker();
    @FXML
    public TextField playTimeField = new TextField();
    @FXML
    public TextField priceField = new TextField();
    @FXML
    public ChoiceBox locationField = new ChoiceBox();
    ObservableList<String> locationItems = FXCollections.observableArrayList();
    @FXML
    public Button saveMovieButton;

    // Variable Recruit Admin
    @FXML
    public TextField usernameField = new TextField();
    @FXML
    public TextField nrpField = new TextField();
    @FXML
    public PasswordField passwordField = new PasswordField();
    @FXML
    public Button recruitUserButton;
    @FXML
    public Button searchUserButton;
    @FXML
    public TextField phoneField = new TextField();

    // Variable untuk customer page
    @FXML
    TableView customerTableView = new TableView();
    ObservableList<Map<String, Object>> customerItems = FXCollections.<Map<String, Object>>observableArrayList();

    // Inisialisasi data page" user untuk setiap url
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(url.toString().contains("AdminMovieView")){
            setMovieTableView();
        }
        else if(url.toString().contains("AdminTransactionView")){
            setTransactionTableView();
        }
        else if(url.toString().contains("AdminCustomerView")){
            setCustomerTableView();
        }
        else if(url.toString().contains("MovieModal")){
            setMoviePopup();
        }
        else if(url.toString().contains("AdminRecruitmentView")){
            setProfileData();
        }
    }

    // Function untuk setup data pada setiap page
    public void setMovieTableView(){
        Platform.runLater(() -> {
            TableColumn<Map, String> idColumn = new TableColumn<>("id");
            idColumn.setCellValueFactory(new MapValueFactory<>("id"));
            TableColumn<Map, String> nameColumn = new TableColumn<>("name");
            nameColumn.setCellValueFactory(new MapValueFactory<>("name"));
            TableColumn<Map, String> startColumn = new TableColumn<>("start_date");
            startColumn.setCellValueFactory(new MapValueFactory<>("start_date"));
            TableColumn<Map, String> endColumn = new TableColumn<>("end_date");
            endColumn.setCellValueFactory(new MapValueFactory<>("end_date"));
            TableColumn<Map, String> timeColumn = new TableColumn<>("playing_time");
            timeColumn.setCellValueFactory(new MapValueFactory<>("playing_time"));
            TableColumn<Map, String> priceColumn = new TableColumn<>("price (Rp)");
            priceColumn.setCellValueFactory(new MapValueFactory<>("price"));
            TableColumn<Map, String> locationColumn = new TableColumn<>("location");
            locationColumn.setCellValueFactory(new MapValueFactory<>("location"));
            TableColumn<Map, String> seatColumn = new TableColumn<>("available_seat");
            seatColumn.setCellValueFactory(new MapValueFactory<>("available_seat"));
            TableColumn<Map, String> action = new TableColumn<>("action");
            action.setCellValueFactory(new MapValueFactory<>("action"));

            movieTableView.getColumns().add(idColumn);
            movieTableView.getColumns().add(nameColumn);
            movieTableView.getColumns().add(startColumn);
            movieTableView.getColumns().add(endColumn);
            movieTableView.getColumns().add(timeColumn);
            movieTableView.getColumns().add(priceColumn);
            movieTableView.getColumns().add(locationColumn);
            movieTableView.getColumns().add(seatColumn);
            movieTableView.getColumns().add(action);

            MovieRepository movieRepository = new MovieRepository();
            BookingRepository bookingRepository = new BookingRepository();
            try {
                List<Movie> movies = (List<Movie>) movieRepository.get();
                for (Movie movie: movies) {
                    Map<String, Object> item = new HashMap<>();
                    item.put("id", movie.getId());
                    item.put("name", movie.getName());
                    item.put("start_date" , movie.getStart_date().toString());
                    item.put("end_date", movie.getEnd_date().toString());
                    item.put("playing_time" , movie.getPlaying_time().toString());
                    item.put("price", movie.getPrice());
                    item.put("location" ,  movie.getLocation().getBuilding()+"-"+ movie.getLocation().getRoom());

                    Integer booked_seat = (Integer) bookingRepository.getBookedSeatForMovieId(movie.getId());
                    item.put("available_seat" ,  (movie.getLocation().getTotal_seat() - booked_seat));

                    // Cancel button
                    Button button = new Button();
                    button.setId(movie.getId()+"");
                    button.setText("X Delete");
                    button.setTextFill(Color.WHITE);
                    button.setStyle("-fx-background-color: red;-fx-padding: 5px 10px;");
                    button.setOnMouseClicked(mouseEvent ->{
                        try {
                            MovieRepository movRepo = new MovieRepository();
                            int movID = Integer.parseInt(button.getId());
                            movRepo.delete(movID);
                            homeButtonClicked();
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    item.put("action" , button);

                    movieItems.add(item);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            movieTableView.getItems().addAll(movieItems);
        });
        movieTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2){
                    // GET SELECTED MOVIE
                    Map<String, Object> movie = (Map<String, Object>) movieTableView.getSelectionModel().getSelectedItem();
                    MovieRepository movieRepository = new MovieRepository();
                    int movieId = (int) movie.get("id");

                    try {
                        SelectedMovie.setMovie((Movie) movieRepository.get(movieId));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    // OPEN TRANSACTION PAGE
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(Main.class.getResource("MovieModal.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Scene scene = new Scene(root, 1280, 720);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.setTitle("Movie Editor");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();

                    // update movie table
                    updateMovieTableView();
                    errorMessageLabel.setText("* Movie berhasil diupdate!");
                    errorMessageLabel.setTextFill(Color.GREEN);
                }
            }
        });
    }
    public void updateMovieTableView(){
        // Update tabel
        MovieRepository movieRepository = new MovieRepository();
        BookingRepository bookingRepository = new BookingRepository();
        try {
            List<Movie> movies = (List<Movie>) movieRepository.get();
            for (int i = 0; i < movies.size(); i++) {
                Movie movie = movies.get(i);
                movieItems.get(i).replace("id", movie.getId());
                movieItems.get(i).replace("name", movie.getName());
                movieItems.get(i).replace("start_date" , movie.getStart_date().toString());
                movieItems.get(i).replace("end_date", movie.getEnd_date().toString());
                movieItems.get(i).replace("playing_time" , movie.getPlaying_time().toString());
                movieItems.get(i).replace("price", movie.getPrice());
                movieItems.get(i).replace("location" ,  movie.getLocation().getBuilding()+"-"+ movie.getLocation().getRoom());

                Integer booked_seat = (Integer) bookingRepository.getBookedSeatForMovieId(movie.getId());
                movieItems.get(i).replace("available_seat" ,  (movie.getLocation().getTotal_seat() - booked_seat));
            }
            movieTableView.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setTransactionTableView() {
        Platform.runLater(() -> {
            TableColumn<Map, String> idColumn = new TableColumn<>("id");
            idColumn.setCellValueFactory(new MapValueFactory<>("id"));
            TableColumn<Map, String> movie = new TableColumn<>("movie");
            movie.setCellValueFactory(new MapValueFactory<>("movie"));
            TableColumn<Map, String> booked_seat = new TableColumn<>("booked_seat");
            booked_seat.setCellValueFactory(new MapValueFactory<>("booked_seat"));
            TableColumn<Map, String> total_price = new TableColumn<>("total_price (Rp)");
            total_price.setCellValueFactory(new MapValueFactory<>("total_price"));
            TableColumn<Map, String> payment_method = new TableColumn<>("payment_method");
            payment_method.setCellValueFactory(new MapValueFactory<>("payment_method"));
            TableColumn<Map, String> action = new TableColumn<>("action");
            action.setCellValueFactory(new MapValueFactory<>("action"));

            transactionTableView.getColumns().add(idColumn);
            transactionTableView.getColumns().add(movie);
            transactionTableView.getColumns().add(booked_seat);
            transactionTableView.getColumns().add(total_price);
            transactionTableView.getColumns().add(payment_method);
            transactionTableView.getColumns().add(action);

            BookingRepository bookingRepository = new BookingRepository();
            try {
                List<Booking> bookings = (List<Booking>) bookingRepository.get();
                for (Booking booking: bookings) {
                    Map<String, Object> item = new HashMap<>();
                    item.put("id", booking.getId());
                    item.put("movie", booking.getMovie().getName());
                    item.put("booked_seat" , booking.getBooked_seat());
                    item.put("total_price", booking.getTotal_price());
                    item.put("payment_method" , booking.getPaymentMethod().getName());

                    // DELETE button
                    Button button = new Button();
                    button.setId(booking.getId()+"");
                    button.setText("X Delete");
                    button.setTextFill(Color.WHITE);
                    button.setStyle("-fx-background-color: red;-fx-padding: 5px 10px;");
                    button.setOnMouseClicked(mouseEvent ->{
                        try {
                            BookingRepository bookRepo = new BookingRepository();
                            int bookingID = Integer.parseInt(button.getId());
                            bookRepo.delete(bookingID);
                            transactionButtonClicked();
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    item.put("action" , button);
                    transactionItems.add(item);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            transactionTableView.getItems().addAll(transactionItems);
        });
    }
    public void setCustomerTableView(){
        Platform.runLater(() -> {
            TableColumn<Map, String> idCol = new TableColumn<>("id");
            idCol.setCellValueFactory(new MapValueFactory<>("id"));
            TableColumn<Map, String> nrpCol = new TableColumn<>("nrp");
            nrpCol.setCellValueFactory(new MapValueFactory<>("nrp"));
            TableColumn<Map, String> nameCol = new TableColumn<>("name");
            nameCol.setCellValueFactory(new MapValueFactory<>("name"));
            TableColumn<Map, String> phoneCol = new TableColumn<>("phone");
            phoneCol.setCellValueFactory(new MapValueFactory<>("phone"));
            TableColumn<Map, String> roleCol = new TableColumn<>("role");
            roleCol.setCellValueFactory(new MapValueFactory<>("role"));

            customerTableView.getColumns().add(idCol);
            customerTableView.getColumns().add(nrpCol);
            customerTableView.getColumns().add(nameCol);
            customerTableView.getColumns().add(phoneCol);
            customerTableView.getColumns().add(roleCol);

            UserRepository userRepository = new UserRepository();
            try {
                List<User> users = (List<User>) userRepository.get();
                for (User user: users) {
                    Map<String, Object> item = new HashMap<>();
                    item.put("id", user.getId());
                    item.put("nrp" , user.getNrp());
                    item.put("name", user.getName());
                    item.put("phone", user.getPhone());
                    item.put("role" , user.getRole() == 0 ? "Admin" : "Customer" );
                    customerItems.add(item);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            customerTableView.getItems().addAll(customerItems);
        });
        customerTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2){
                    // GET SELECTED MOVIE
                    Map<String, Object> user = (Map<String, Object>) customerTableView.getSelectionModel().getSelectedItem();
                    // check is admin
                    String role = user.get("role").toString();
                    if(role.equalsIgnoreCase("Admin")){
                        errorMessageLabel.setText("* user ini sudah menjadi admin!");
                        return;
                    }

                    UserRepository userRepository = new UserRepository();
                    int userID = (int) user.get("id");
                    try {
                        SelectedUser.setUser((User) userRepository.get(userID));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    Stage stageTheLabelBelongs = (Stage) homeButton.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AdminRecruitmentView.fxml"));
                    stageTheLabelBelongs.setResizable(false);
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 1280, 720);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stageTheLabelBelongs.setScene(scene);
                }
            }
        });
    }
    public void setMoviePopup(){
        Platform.runLater(() -> {
            LocationRepository locationRepository = new LocationRepository();
            try {
                List<Location> locations = (List<Location>) locationRepository.get();
                for (Location location: locations) {
                    locationItems.add(location.getBuilding()+" - "+location.getRoom());
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            locationField.getItems().addAll(locationItems);
            locationField.getSelectionModel().select(0);

            if(SelectedMovie.getMovie() != null){
                Image image = new Image(SelectedMovie.getMovie().getImage());
                movieImageView.setImage(image);
                movieIdField.setText(SelectedMovie.getMovie().getId()+"");
                movieNameField.setText(SelectedMovie.getMovie().getName());
                imageTextField.setText(SelectedMovie.getMovie().getImage());
                playTimeField.setText(SelectedMovie.getMovie().getPlaying_time()+"");
                priceField.setText(SelectedMovie.getMovie().getPrice()+"");
                locationField.getSelectionModel().select(SelectedMovie.getMovie().getLocation().getId()-1);

            }else{
                var parent = imageContainer.getParent();
                if(parent instanceof Pane pane){
                    pane.getChildren().remove(imageContainer);
                }
            }
        });
    }
    public void setProfileData(){
        Platform.runLater(() -> {
            usernameField.setEditable(false);
            phoneField.setEditable(false);

            if(SelectedUser.getUser() != null){
                usernameField.setText(SelectedUser.getUser().getName());
                phoneField.setText(SelectedUser.getUser().getPhone());
                nrpField.setText(SelectedUser.getUser().getNrp());
                nrpField.setEditable(false);
            }
        });
    }

    // Function Navigasi antar page
    @FXML
    private void homeButtonClicked() throws IOException {
        Stage stageTheLabelBelongs = (Stage) homeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AdminMovieView.fxml"));
        stageTheLabelBelongs.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stageTheLabelBelongs.setScene(scene);
    }
    @FXML
    private void transactionButtonClicked() throws IOException {
        Stage stageTheLabelBelongs = (Stage) transactionButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AdminTransactionView.fxml"));
        stageTheLabelBelongs.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stageTheLabelBelongs.setScene(scene);
    }
    @FXML
    private void customerButtonClicked() throws IOException {
        Stage stageTheLabelBelongs = (Stage) customerButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AdminCustomerView.fxml"));
        stageTheLabelBelongs.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stageTheLabelBelongs.setScene(scene);
    }
    @FXML
    private void recruitButtonClicked() throws IOException {
        SelectedUser.setUser(null);
        Stage stageTheLabelBelongs = (Stage) customerButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AdminRecruitmentView.fxml"));
        stageTheLabelBelongs.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stageTheLabelBelongs.setScene(scene);
    }
    @FXML
    private void addNewMovieButtonClick() throws IOException {
        SelectedMovie.setMovie(null);
        // OPEN MOVIE EDITOR
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("MovieModal.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root, 1024, 720);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Movie Editor");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        homeButtonClicked();
    }
    @FXML
    private void logoutButtonClicked() throws IOException {
        Stage stageTheLabelBelongs = (Stage) logoutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        stageTheLabelBelongs.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stageTheLabelBelongs.setScene(scene);
        UserLogin.logout();
    }

    // Function Recruit Admin
    @FXML
    private void recruitUserButtonClicked() throws SQLException {
        UserRepository userRepository = new UserRepository();

        if(!passwordField.getText().equals(UserLogin.getUser().getPassword())){
            errorMessageLabel.setText("* Admin Password invalid");
            return;
        }

        if(SelectedUser.getUser() == null){
            List<User> searchedUser = (List<User>) userRepository.getByNrp(nrpField.getText());
            if(searchedUser.isEmpty()){
                errorMessageLabel.setText("* User dengan NRP "+nrpField.getText() +" tidak ditemukan!");
                return;
            }
            User newUser = searchedUser.get(0);
            newUser.setRole(0);
            userRepository.update(newUser);
            errorMessageLabel.setTextFill(Color.GREEN);
            errorMessageLabel.setText("* "+newUser.getName()+" berhasil menjadi Admin!");

            usernameField.setText("");
            nrpField.setText("");
            phoneField.setText("");
            passwordField.setText("");
        }else{
            try {
                SelectedUser.getUser().setRole(0);
                userRepository.update(SelectedUser.getUser());
                errorMessageLabel.setTextFill(Color.GREEN);
                errorMessageLabel.setText("* "+SelectedUser.getUser().getName()+" berhasil menjadi Admin!");

                usernameField.setText("");
                nrpField.setText("");
                phoneField.setText("");
                passwordField.setText("");
            }
            catch (SQLException e){
                errorMessageLabel.setText("Gagal update!");
            }
        }
    }
    @FXML
    private void searchUserButtonCliked() throws SQLException {
        UserRepository userRepository = new UserRepository();

        if(SelectedUser.getUser() == null){
            List<User> searchedUser = (List<User>) userRepository.getByNrp(nrpField.getText());
            if(searchedUser.isEmpty()){
                errorMessageLabel.setText("* User dengan NRP "+nrpField.getText() +" tidak ditemukan!");
                return;
            }
            User newUser = searchedUser.get(0);
            if(newUser.getRole() == 0 ){
                errorMessageLabel.setText("* User dengan NRP "+nrpField.getText() +" adalah admin!");
                return;
            }
            SelectedUser.setUser(newUser);

            usernameField.setText(newUser.getName());
            phoneField.setText(newUser.getPhone());

            errorMessageLabel.setTextFill(Color.GREEN);
            errorMessageLabel.setText("* User dengan NRP "+newUser.getNrp()+" ditemukan!");
        }
    }

    // Function Movie Editor
    @FXML
    private void saveMovieButtonClicked() throws SQLException {
        if(!movieInputValid()){
            return;
        }
        MovieRepository movieRepository = new MovieRepository();
        int locationIndex = locationField.getSelectionModel().getSelectedIndex()+1;
        LocationRepository locationRepository = new LocationRepository();
        Location location = (Location) locationRepository.get(locationIndex);

        if(SelectedMovie.getMovie() != null){
            LocalTime time = LocalTime.parse(playTimeField.getText());
            Time playTime = Time.valueOf(time);

            SelectedMovie.getMovie().setName(movieNameField.getText());
            SelectedMovie.getMovie().setImage(imageTextField.getText());
            SelectedMovie.getMovie().setPlaying_time(playTime);
            SelectedMovie.getMovie().setPrice(Double.parseDouble(priceField.getText()));
            SelectedMovie.getMovie().setLocation(location);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(startDateField.getValue().toString(), formatter);
            LocalDate endDate = LocalDate.parse(endDateField.getValue().toString(), formatter);
            Timestamp startDT = Timestamp.valueOf(startDate.atStartOfDay());
            Timestamp endDT = Timestamp.valueOf(endDate.atStartOfDay());
            SelectedMovie.getMovie().setStart_date(startDT);
            SelectedMovie.getMovie().setEnd_date(endDT);

            movieRepository.update(SelectedMovie.getMovie());
        }else{
            Movie movie = new Movie();
            movie.setName(movieNameField.getText());
            movie.setImage(imageTextField.getText());

            LocalTime time = LocalTime.parse(playTimeField.getText());
            Time playTime = Time.valueOf(time);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(startDateField.getValue().toString(), formatter);
            LocalDate endDate = LocalDate.parse(endDateField.getValue().toString(), formatter);
            Timestamp startDT = Timestamp.valueOf(startDate.atStartOfDay());
            Timestamp endDT = Timestamp.valueOf(endDate.atStartOfDay());
            movie.setStart_date(startDT);
            movie.setEnd_date(endDT);

            movie.setPlaying_time(playTime);
            movie.setPrice(Double.parseDouble(priceField.getText()));
            movie.setLocation(location);

            movieRepository.add(movie);

        }
        Stage stageTheLabelBelongs = (Stage) saveMovieButton.getScene().getWindow();
        stageTheLabelBelongs.close();
    }

    public boolean movieInputValid(){
        errorMessageLabel.setText("");
        if(movieNameField.getText().isEmpty()){
            errorMessageLabel.setText("Nama movie tidak boleh kosong!");
            return false;
        }
        if(imageTextField.getText().isEmpty()){
            errorMessageLabel.setText("Image movie tidak boleh kosong!");
            return false;
        }
        try {
            Image image = new Image(imageTextField.getText());
        }catch (Exception e){
            errorMessageLabel.setText("Image movie harus url yang valid!");
            return false;
        }
        if(startDateField.getValue() == null){
            errorMessageLabel.setText("Start Date tidak boleh kosong!");
            return false;
        }
        if(endDateField.getValue() == null){
            errorMessageLabel.setText("End Date tidak boleh kosong!");
            return false;
        }
        if(playTimeField.getText().isEmpty()){
            errorMessageLabel.setText("Play time tidak boleh kosong!");
            return false;
        }
        if(!playTimeField.getText().matches("\\d{2}:\\d{2}:\\d{2}")){
            errorMessageLabel.setText("Format play time salah! contoh: 22:10:22");
            return false;
        }
        if(priceField.getText().isEmpty()){
            errorMessageLabel.setText("Price tidak boleh kosong!");
            return false;
        }
        return true;
    }

}
