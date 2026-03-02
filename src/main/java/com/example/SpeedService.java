package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SpeedService {

    private static final String URL =
            "jdbc:mariadb://db:3306/speed1_db";
    private static final String USER = "root";
    private static final String PASS = "Test12";

    public void saveSpeed(double distance, double time, double speed) {

        String sql = "INSERT INTO speed_records(distance, time, speed) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, distance);
            stmt.setDouble(2, time);
            stmt.setDouble(3, speed);
            stmt.executeUpdate();

            System.out.println("Saved to database!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}