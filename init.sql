-- Create database if it does not exist
CREATE DATABASE IF NOT EXISTS speed1_db;

-- Use the database
USE speed1_db;

-- Create speeds table
CREATE TABLE IF NOT EXISTS speeds (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      distance DOUBLE NOT NULL,
                                      time DOUBLE NOT NULL,
                                      average_speed DOUBLE NOT NULL,
                                      timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);