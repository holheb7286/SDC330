/*******************************************************************
 * Name: Holly Hebert
 * Date: December 10, 2025
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
    private final Scanner scanner;

    public PetManager(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
        this.scanner = new Scanner(System.in);
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

            // Fetch current data
            String fetchSql = "SELECT * FROM pets WHERE pet_id = ?;";
            try (PreparedStatement fetchPs = conn.prepareStatement(fetchSql)) {
                fetchPs.setInt(1, petId);
                ResultSet rs = fetchPs.executeQuery();

                if (!rs.next()) {
                    System.out.println("No pet found with that ID.");
                    return;
                }

                // Show current values
                String currentName = rs.getString("name");
                String currentSpecies = rs.getString("species");
                String currentBreed = rs.getString("breed");
                int currentAge = rs.getInt("age");
                double currentWeight = rs.getDouble("weight");

                // Prompt user for new values
                System.out.println("\n--- Leave any field blank to keep current value ---");

                System.out.print("New Name [" + currentName + "]: ");
                String name = scanner.nextLine();
                if (name.isEmpty()) name = currentName;

                System.out.print("New Species [" + currentSpecies + "]: ");
                String species = scanner.nextLine();
                if (species.isEmpty()) species = currentSpecies;

                System.out.print("New Breed [" + currentBreed + "]: ");
                String breed = scanner.nextLine();
                if (breed.isEmpty()) breed = currentBreed;

                System.out.print("New Age [" + currentAge + "]: ");
                String ageInput = scanner.nextLine();
                int age = ageInput.isEmpty() ? currentAge : Integer.parseInt(ageInput);

                System.out.print("New Weight [" + currentWeight + "]: ");
                String weightInput = scanner.nextLine();
                double weight = weightInput.isEmpty() ? currentWeight : Double.parseDouble(weightInput);

                String updateSql = "UPDATE pets SET name = ?, species = ?, breed = ?, age = ?, weight = ? WHERE pet_id = ?;";
                try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
                    updatePs.setString(1, name);
                    updatePs.setString(2, species);
                    updatePs.setString(3, breed);
                    updatePs.setInt(4, age);
                    updatePs.setDouble(5, weight);
                    updatePs.setInt(6, petId);

                    int rows = updatePs.executeUpdate();
                    System.out.println(rows > 0 ? "Pet updated successfully." : "Update failed.");
                }
            }

        } catch (SQLException | NumberFormatException e) {
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