/*******************************************************************
 * Name: Holly Hebert
 * Date: December 5, 2025
 * Assignment: SDC330 Week 4 – PetManager (with DB support)
 * Class: PetManager
 *
 * Description:
 * Handles all CRUD operations for pets stored in an SQLite database.
 * Uses a shared Scanner to avoid input stream closure errors.
 *******************************************************************/

import java.sql.*;
import java.util.Scanner;

public class PetManager implements Manageable {

    private final DatabaseHelper dbHelper;
    private final Scanner scanner;   // <- ONE shared scanner

    public PetManager(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
        this.scanner = new Scanner(System.in);   // ← Never close this
        dbHelper.initializeDatabase();
    }

    // ========================= ADD PET ===============================
    @Override
    public void add() {
        try (Connection conn = dbHelper.getConnection()) {

            System.out.println("--- Add a New Pet ---");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Species (Dog or Cat): ");
            String species = scanner.nextLine();

            System.out.print("Breed: ");
            String breed = scanner.nextLine();

            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Weight (in pounds): ");
            double weight = Double.parseDouble(scanner.nextLine());

            String sql = "INSERT INTO pets(name, species, breed, age, weight) VALUES (?, ?, ?, ?, ?);";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, species);
                ps.setString(3, breed);
                ps.setInt(4, age);
                ps.setDouble(5, weight);

                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Pet added successfully." : "Failed to add pet.");
            }

        } catch (SQLException e) {
            System.err.println("Error adding pet: " + e.getMessage());
        }
    }

    // ========================= VIEW ALL PETS ===========================
    @Override
    public void view() {
        System.out.println("--- View All Pets ---");

        String sql = "SELECT * FROM pets;";

        try (Connection conn = dbHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            while (rs.next()) {
                int id = rs.getInt("pet_id");
                String name = rs.getString("name");
                String species = rs.getString("species");
                String breed = rs.getString("breed");
                int age = rs.getInt("age");
                double weight = rs.getDouble("weight");

                System.out.printf(
                    "ID: %d | Name: %s | Species: %s | Breed: %s | Age: %d | Weight: %.2f lbs%n",
                    id, name, species, breed, age, weight
                );

                found = true;
            }

            if (!found) {
                System.out.println("No pets found in the system.");
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving pets: " + e.getMessage());
        }
    }

    // ========================= UPDATE PET ===============================
    @Override
    public void update() {
        try (Connection conn = dbHelper.getConnection()) {

            System.out.print("Enter Pet ID to update: ");
            int petId = Integer.parseInt(scanner.nextLine());

            System.out.print("New Name: ");
            String name = scanner.nextLine();

            System.out.print("New Species: ");
            String species = scanner.nextLine();

            System.out.print("New Breed: ");
            String breed = scanner.nextLine();

            System.out.print("New Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("New Weight: ");
            double weight = Double.parseDouble(scanner.nextLine());

            String sql = "UPDATE pets SET name = ?, species = ?, breed = ?, age = ?, weight = ? WHERE pet_id = ?;";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, species);
                ps.setString(3, breed);
                ps.setInt(4, age);
                ps.setDouble(5, weight);
                ps.setInt(6, petId);

                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Pet updated." : "No such pet ID.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating pet: " + e.getMessage());
        }
    }

    // ========================= DELETE PET ===============================
    @Override
    public void delete() {
        try (Connection conn = dbHelper.getConnection()) {

            System.out.print("Enter Pet ID to delete: ");
            int petId = Integer.parseInt(scanner.nextLine());

            String sql = "DELETE FROM pets WHERE pet_id = ?;";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, petId);

                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Pet deleted." : "No such pet ID.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting pet: " + e.getMessage());
        }
    }
}