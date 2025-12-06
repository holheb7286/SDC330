/*******************************************************************
 * Name: Holly Hebert
 * Date: December 5, 2025
 * Assignment: SDC330 Week 4 – Animal Health Checker Database Helper
 * Class: DatabaseHelper
 *
 * Description:
 * Handles SQLite connection and schema creation for the Pet Health Checker app.
 * Ensures tables for pets, appointments, medical records, and emergency contacts
 * are present. Provides connection access to other classes like PetManager.
 *******************************************************************/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

    private static final String DB_URL = "jdbc:sqlite:petsystem.db";

    // Returns a new database connection
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Called once at startup to create tables if they don't exist
    public void initializeDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

            // Pets table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS pets (
                    pet_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    species TEXT NOT NULL,
                    breed TEXT,
                    age INTEGER,
                    weight REAL
                );
            """);

            // Appointments table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS appointments (
                    appointment_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    pet_id INTEGER,
                    date TEXT,
                    reason TEXT,
                    FOREIGN KEY (pet_id) REFERENCES pets(pet_id)
                );
            """);

            // Medical Records table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS medical_records (
                    record_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    pet_id INTEGER,
                    condition TEXT,
                    treatment TEXT,
                    date TEXT,
                    FOREIGN KEY (pet_id) REFERENCES pets(pet_id)
                );
            """);

            // Emergency Contacts table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS emergency_contacts (
                    contact_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    pet_id INTEGER,
                    name TEXT,
                    phone TEXT,
                    relationship TEXT,
                    FOREIGN KEY (pet_id) REFERENCES pets(pet_id)
                );
            """);

            System.out.println("Database initialized successfully.");

        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }
}