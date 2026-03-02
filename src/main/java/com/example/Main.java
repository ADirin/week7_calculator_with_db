package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main extends Application {

    // ===== DATABASE CONFIG =====
    private static final String URL =
            "jdbc:mariadb://db:3306/speed1_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Test12";

    // ===== CALCULATION =====
    public static double speedAverage(double distance, double time) {
        return distance / time;
    }

    // ===== SAVE TO DATABASE =====
    private void saveToDatabase(double distance, double time, double speed) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String sql = "INSERT INTO speeds(distance, time, average_speed) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, distance);
            stmt.setDouble(2, time);
            stmt.setDouble(3, speed);

            stmt.executeUpdate();

            System.out.println("Saved to database!");

        } catch (Exception ex) {
            ex.printStackTrace();  // VERY IMPORTANT for debugging
        }
    }

    // ===== UI =====
    @Override
    public void start(Stage stage) {

        Label distanceLabel = new Label("Enter Distance:");
        TextField distanceField = new TextField();

        Label timeLabel = new Label("Enter Time:");
        TextField timeField = new TextField();

        Button calculateButton = new Button("Calculate Average Speed");
        Label resultLabel = new Label();

        calculateButton.setOnAction(e -> {
            try {
                double distance = Double.parseDouble(distanceField.getText());
                double time = Double.parseDouble(timeField.getText());

                if (time == 0) {
                    resultLabel.setText("Time cannot be zero!");
                    return;
                }

                double speed = speedAverage(distance, time);

                // SAVE TO DATABASE
                saveToDatabase(distance, time, speed);

                resultLabel.setText("Average Speed: " + speed);

            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input!");
            }
        });

        VBox root = new VBox(10,
                distanceLabel, distanceField,
                timeLabel, timeField,
                calculateButton,
                resultLabel
        );

        Scene scene = new Scene(root, 350, 250);
        stage.setTitle("Average Speed Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}