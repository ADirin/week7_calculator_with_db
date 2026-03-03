package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private final SpeedService speedService = new SpeedService();

    // ===== CALCULATION =====
    public static double speedAverage(double distance, double time) {
        return distance / time;
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
                speedService.saveSpeed(distance, time, speed);

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