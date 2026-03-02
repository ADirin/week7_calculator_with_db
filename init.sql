-- Create database if it does not exist
CREATE DATABASE IF NOT EXISTS speed1_db;

-- Use the database
USE speed1_db;

-- Create speeds table
CREATE TABLE IF NOT EXISTS speed_records (
  id INT AUTO_INCREMENT PRIMARY KEY,
  distance DOUBLE NOT NULL,
  time DOUBLE NOT NULL,
  speed DOUBLE NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);