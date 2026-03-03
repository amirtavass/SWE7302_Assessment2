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
/*
this file is responsible to create ui env using javafx dependencies
 */

public class CinemaDashboard extends Application {

    //first we Connect to our Facade
    private final CinemaApiFacade facade = new CinemaApiFacade();
    private final TicketBuilder builder = new TicketBuilder();

    // UI State
    private String selectedMovie = null;
    private TabPane tabPane;
    private TextArea outputLog;

    // Map to link Movie Titles to Image Files (Simulating a DB image link)
    private final Map<String, String> moviePosters = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("StarScreen Cinema Manager (Assessment 2)");

        // Initialize Poster Map (Ensure these files exist in src/main/resources/images/)
        // If an image is missing, the app will just show a placeholder text.
        moviePosters.put("Dune: Part Two", "dune2.jpg");
        moviePosters.put("Oppenheimer", "oppenheimer.jpg");
        moviePosters.put("Spider-Man: Across the Spider-Verse", "spiderman.jpg");
        moviePosters.put("The Matrix Remastered", "matrix.jpg");
        moviePosters.put("Interstellar (IMAX Re-release)", "interstellar.jpg");
        moviePosters.put("Avatar: The Way of Water", "avatar.jpg");

        // --- MAIN LAYOUT ---
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Create Tabs
        Tab tab1 = createMovieSelectionTab();
        Tab tab2 = createAddonsTab();
        Tab tab3 = createPaymentTab();
        Tab tab4 = createConfirmationTab();

        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);

        Scene scene = new Scene(tabPane, 500, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // --- TAB 1: MOVIE SELECTION ---
    private Tab createMovieSelectionTab() {
        Tab tab = new Tab("1. Select Movie");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label lblTitle = new Label("Now Showing");
        lblTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Movie List
        ListView<String> movieList = new ListView<>();
        movieList.getItems().addAll(facade.getAvailableMovies());

        // Image View for Poster
        ImageView posterView = new ImageView();
        posterView.setFitHeight(250);
        posterView.setFitWidth(180);
        posterView.setPreserveRatio(true);

        // Handle Selection
        movieList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedMovie = newVal;
                loadPoster(newVal, posterView);
            }
        });

        Button btnNext = new Button("Next: Customize Ticket >");
        styleButton(btnNext);
        btnNext.setOnAction(e -> {
            if (selectedMovie == null) {
                showAlert("Please select a movie first!");
            } else {
                tabPane.getSelectionModel().select(1); // Go to next tab
            }
        });

        layout.getChildren().addAll(lblTitle, movieList, posterView, btnNext);
        tab.setContent(layout);
        return tab;
    }

    // --- TAB 2: ADD-ONS (BUILDER PATTERN) ---
    private Tab createAddonsTab() {
        Tab tab = new Tab("2. Customize");

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);

        Label lblHeader = new Label("Customize Your Experience");
        lblHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Ticket Type
        VBox typeBox = new VBox(10);
        typeBox.setStyle("-fx-border-color: #ddd; -fx-padding: 15; -fx-border-radius: 5;");
        Label lblType = new Label("Choose Format:");
        RadioButton rStandard = new RadioButton("Standard (£10)");
        RadioButton rImax = new RadioButton("IMAX (£15) - Recommended");
        ToggleGroup group = new ToggleGroup();
        rStandard.setToggleGroup(group);
        rImax.setToggleGroup(group);
        rStandard.setSelected(true);
        typeBox.getChildren().addAll(lblType, rStandard, rImax);

        // Snacks (Decorators)
        VBox snackBox = new VBox(10);
        snackBox.setStyle("-fx-border-color: #ddd; -fx-padding: 15; -fx-border-radius: 5;");
        Label lblSnack = new Label("Add-ons:");
        CheckBox chkPopcorn = new CheckBox("Large Popcorn (+£6.50)");
        CheckBox chkGlasses = new CheckBox("3D Glasses (+£2.00)");
        snackBox.getChildren().addAll(lblSnack, chkPopcorn, chkGlasses);

        Button btnNext = new Button("Next: Payment >");
        styleButton(btnNext);
        btnNext.setOnAction(e -> {
            // Apply Builder Logic
            if (rImax.isSelected()) builder.setType("IMAX");
            else builder.setType("Standard");

            if (chkPopcorn.isSelected()) builder.addPopcorn();
            if (chkGlasses.isSelected()) builder.addGlasses();

            // Go to next tab
            tabPane.getSelectionModel().select(2);
        });

        layout.getChildren().addAll(lblHeader, typeBox, snackBox, btnNext);
        tab.setContent(layout);
        return tab;
    }

    // --- TAB 3: DISCOUNT & CONFIRM (STRATEGY PATTERN) ---
    private Tab createPaymentTab() {
        Tab tab = new Tab("3. Payment");

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);

        Label lblHeader = new Label("Final Details");
        lblHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        CheckBox chkStudent = new CheckBox("I am a Student (Save 20%)");
        chkStudent.setStyle("-fx-font-size: 14px;");

        Label lblEmail = new Label("Email for confirmation:");
        TextField txtEmail = new TextField();
        txtEmail.setPromptText("example@email.com");

        Button btnFinish = new Button("Confirm & Pay");
        btnFinish.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20;");

        btnFinish.setOnAction(e -> {
            if (txtEmail.getText().isEmpty()) {
                showAlert("Please enter an email address.");
                return;
            }

            // 1. Build Final Ticket
            ITicket ticket = builder.build();

            // 2. Call Facade
            boolean isStudent = chkStudent.isSelected();
            String result = facade.bookTicket(ticket, selectedMovie, isStudent);

            // 3. Show Result
            outputLog.setText(result);
            tabPane.getSelectionModel().select(3); // Go to final tab
        });

        layout.getChildren().addAll(lblHeader, chkStudent, new Separator(), lblEmail, txtEmail, new Separator(), btnFinish);
        tab.setContent(layout);
        return tab;
    }

    // --- TAB 4: RECEIPT ---
    private Tab createConfirmationTab() {
        Tab tab = new Tab("4. Receipt");

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label lblSuccess = new Label("Booking Confirmed! 🎉");
        lblSuccess.setStyle("-fx-text-fill: green; -fx-font-size: 22px; -fx-font-weight: bold;");

        outputLog = new TextArea();
        outputLog.setEditable(false);
        outputLog.setWrapText(true);
        outputLog.setPrefHeight(300);

        Button btnRestart = new Button("Book Another Ticket");
        btnRestart.setOnAction(e -> {
            // Reset Wizard
            tabPane.getSelectionModel().select(0);
            selectedMovie = null;
        });

        layout.getChildren().addAll(lblSuccess, outputLog, btnRestart);
        tab.setContent(layout);
        return tab;
    }

    // --- HELPERS ---
    private void loadPoster(String movieTitle, ImageView view) {
        String fileName = moviePosters.getOrDefault(movieTitle, "default.jpg");
        try {
            // Load from src/main/resources/images/
            InputStream is = getClass().getResourceAsStream("/images/" + fileName);
            if (is != null) {
                view.setImage(new Image(is));
            } else {
                view.setImage(null); // Clear if not found
            }
        } catch (Exception e) {
            System.out.println("Could not load image: " + fileName);
        }
    }

    private void styleButton(Button btn) {
        btn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8 15;");
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