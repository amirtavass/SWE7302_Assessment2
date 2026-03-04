package com.cinema.modern.ui;

import com.cinema.modern.api.CinemaApiFacade;
import com.cinema.modern.builder.TicketBuilder;
import com.cinema.modern.core.ITicket;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class is responsible for the Graphical User Interface (GUI) for our project.
 * It uses JavaFX packages for that.
 * This file contains NO business logic, database queries, or pricing algorithms.
 */
public class CinemaDashboard extends Application {

    //STRUCTURAL PATTERN:facade

    private final CinemaApiFacade facade = new CinemaApiFacade();

    //UI Navigation
    private BorderPane mainRoot;
    private VBox step1View, step2View, step3View, step4View;

    // Shared State & Inputs
    private String selectedMovie = null;
    //in here we use hashmap with keys and values instead multiple else/if for our posters
    private final Map<String, String> moviePosters = new HashMap<>();


    private RadioButton rStandard, rImax;
    private CheckBox chkPopcorn, chkGlasses, chkStudent;
    private TextField txtEmail;
    private TextArea outputLog;
    private Label lblPriceStep2, lblPriceStep3;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome to TicketManager!");

        //posters of the images
        moviePosters.put("Dune: Part Two", "dune2.jpg");
        moviePosters.put("Oppenheimer", "oppenheimer.jpg");
        moviePosters.put("Spider-Man: Across the Spider-Verse", "spiderman.jpg");
        moviePosters.put("The Matrix Remastered", "matrix.jpg");
        moviePosters.put("Interstellar (IMAX Re-release)", "interstellar.jpg");
        moviePosters.put("Avatar: The Way of Water", "avatar.jpg");


        initializeControls();

        //our 4 Pages
        step1View = createMovieSelectionStep();
        step2View = createAddonsStep();
        step3View = createPaymentStep();
        step4View = createConfirmationStep();

        //Main container
        mainRoot = new BorderPane();
        mainRoot.setCenter(step1View);

        Scene scene = new Scene(mainRoot, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void initializeControls() {

        rStandard = new RadioButton("Standard (£10.00)");
        rImax = new RadioButton("IMAX (£15.00) - Recommended");
        ToggleGroup group = new ToggleGroup();
        rStandard.setToggleGroup(group);
        rImax.setToggleGroup(group);
        rStandard.setSelected(true);

        chkPopcorn = new CheckBox("Large Popcorn (+£6.50)");
        chkGlasses = new CheckBox("3D Glasses (+£2.00)");

        lblPriceStep2 = new Label("Current Total: £10.00");
        lblPriceStep2.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        //our Controls
        chkStudent = new CheckBox("I am a Student (Save 20%)");
        chkStudent.setStyle("-fx-font-size: 14px;");
        txtEmail = new TextField();
        txtEmail.setPromptText("example@email.com");

        lblPriceStep3 = new Label("Final Total: £10.00");
        lblPriceStep3.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #e74c3c;");

        //Listeners to update price automatically when clicked
        rStandard.setOnAction(e -> updatePriceDisplay());
        rImax.setOnAction(e -> updatePriceDisplay());
        chkPopcorn.setOnAction(e -> updatePriceDisplay());
        chkGlasses.setOnAction(e -> updatePriceDisplay());
        chkStudent.setOnAction(e -> updatePriceDisplay());
    }

    //we need this to calculate the price
    private void updatePriceDisplay() {
        double basePrice = rImax.isSelected() ? 15.00 : 10.00;
        if (chkPopcorn.isSelected()) basePrice += 6.50;
        if (chkGlasses.isSelected()) basePrice += 2.00;

        lblPriceStep2.setText(String.format("Current Total: £%.2f", basePrice));

        double finalPrice = chkStudent.isSelected() ? (basePrice * 0.8) : basePrice;
        lblPriceStep3.setText(String.format("Final Total: £%.2f", finalPrice));
    }

    //selecting movie
    private VBox createMovieSelectionStep() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label lblTitle = new Label("Step 1: Select a Movie");
        lblTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        ListView<String> movieList = new ListView<>();
        movieList.getItems().addAll(facade.getAvailableMovies());
        movieList.setPrefHeight(150);

        ImageView posterView = new ImageView();
        posterView.setFitHeight(200);
        posterView.setFitWidth(140);
        posterView.setPreserveRatio(true);

        movieList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedMovie = newVal;
                loadPoster(newVal, posterView);
            }
        });

        Button btnNext = new Button("Next: Customize Ticket >");
        styleButton(btnNext, "#3498db");
        btnNext.setMaxWidth(Double.MAX_VALUE);

        btnNext.setOnAction(e -> {
            if (selectedMovie == null) {
                showAlert("Please select a movie first!");
            } else {
                mainRoot.setCenter(step2View);
            }
        });

        layout.getChildren().addAll(lblTitle, movieList, posterView, btnNext);
        return layout;
    }

    //BUILDER PATTERN for add-ons
    private VBox createAddonsStep() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);

        Label lblHeader = new Label("Step 2: Customize Experience");
        lblHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        VBox typeBox = new VBox(10, new Label("Choose Format:"), rStandard, rImax);
        typeBox.setStyle("-fx-border-color: #ddd; -fx-padding: 15; -fx-border-radius: 5;");

        VBox snackBox = new VBox(10, new Label("Add-ons:"), chkPopcorn, chkGlasses);
        snackBox.setStyle("-fx-border-color: #ddd; -fx-padding: 15; -fx-border-radius: 5;");

        //our navigation Buttons
        Button btnBack = new Button("< Back");
        Button btnNext = new Button("Next: Payment >");
        styleButton(btnBack, "#7f8c8d");
        styleButton(btnNext, "#3498db");

        HBox navBox = new HBox(15, btnBack, btnNext);
        navBox.setAlignment(Pos.CENTER);

        btnBack.setOnAction(e -> mainRoot.setCenter(step1View));
        btnNext.setOnAction(e -> mainRoot.setCenter(step3View));

        layout.getChildren().addAll(lblHeader, typeBox, snackBox, lblPriceStep2, new Separator(), navBox);
        return layout;
    }

    //STRATEGY PATTERN for dicount and reciept
    private VBox createPaymentStep() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);

        Label lblHeader = new Label("Step 3: Final Details");
        lblHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        VBox discountBox = new VBox(10, chkStudent);
        discountBox.setStyle("-fx-border-color: #ddd; -fx-padding: 15; -fx-border-radius: 5;");

        VBox emailBox = new VBox(5, new Label("Email for confirmation:"), txtEmail);

        // Navigation Buttons
        Button btnBack = new Button("< Back");
        Button btnFinish = new Button("Confirm & Pay");
        styleButton(btnBack, "#7f8c8d");
        styleButton(btnFinish, "#27ae60");

        HBox navBox = new HBox(15, btnBack, btnFinish);
        navBox.setAlignment(Pos.CENTER);

        btnBack.setOnAction(e -> mainRoot.setCenter(step2View));

        btnFinish.setOnAction(e -> {
            if (txtEmail.getText().isEmpty()) {
                showAlert("Please enter an email address.");
                return;
            }

            //CREATIONAL PATTERN:builder
            TicketBuilder builder = new TicketBuilder();
            if (rImax.isSelected()) builder.setType("IMAX");
            else builder.setType("Standard");

            if (chkPopcorn.isSelected()) builder.addPopcorn();
            if (chkGlasses.isSelected()) builder.addGlasses();

            ITicket ticket = builder.build();

            //Call Facade
            String result = facade.bookTicket(ticket, selectedMovie, chkStudent.isSelected());

            //Show Result
            outputLog.setText(result);
            mainRoot.setCenter(step4View);
        });

        layout.getChildren().addAll(lblHeader, discountBox, emailBox, lblPriceStep3, new Separator(), navBox);
        return layout;
    }

    //FINAL RECEIPT
    private VBox createConfirmationStep() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label lblSuccess = new Label("Booking Confirmed!");
        lblSuccess.setStyle("-fx-text-fill: green; -fx-font-size: 22px; -fx-font-weight: bold;");

        outputLog = new TextArea();
        outputLog.setEditable(false);
        outputLog.setWrapText(true);
        outputLog.setPrefHeight(200);

        Button btnRestart = new Button("Book Another Ticket");
        styleButton(btnRestart, "#3498db");

        btnRestart.setOnAction(e -> {
            //Reseting our Inputs
            selectedMovie = null;
            rStandard.setSelected(true);
            chkPopcorn.setSelected(false);
            chkGlasses.setSelected(false);
            chkStudent.setSelected(false);
            txtEmail.clear();
            updatePriceDisplay();

            mainRoot.setCenter(step1View);
        });

        layout.getChildren().addAll(lblSuccess, outputLog, btnRestart);
        return layout;
    }

    //
    private void loadPoster(String movieTitle, ImageView view) {
        String fileName = moviePosters.getOrDefault(movieTitle, "default.jpg");
        try {
            InputStream is = getClass().getResourceAsStream("/images/" + fileName);
            if (is != null) {
                view.setImage(new Image(is));
            } else {
                view.setImage(null);
            }
        } catch (Exception e) {
            System.out.println("Could not load image: " + fileName);
        }
    }

    private void styleButton(Button btn, String hexColor) {
        btn.setStyle("-fx-background-color: " + hexColor + "; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8 15; -fx-cursor: hand;");
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}