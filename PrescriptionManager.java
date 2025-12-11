/*******************************************************************
 * Name: Holly Hebert
 * Date: December 10, 2025
 * Assignment: SDC330 Week 5 – Pet Prescription Manager (DB Support)
 * Class: PrescriptionManager
 *
 * Description:
 * Manages prescription records in the SQLite database.
 * Supports full CRUD operations for pet prescriptions.
 *******************************************************************/

import java.sql.*;
import java.util.Scanner;

public class PrescriptionManager {

    private final DatabaseHelper dbHelper;
    private final Scanner scanner;

    public PrescriptionManager(DatabaseHelper dbHelper, Scanner scanner) {
        this.dbHelper = dbHelper;
        this.scanner = scanner;
    }

    // ========================= ADD ===========================
    public void add() {
        try (Connection conn = dbHelper.getConnection()) {

            System.out.println("--- Add a New Prescription ---");

            System.out.print("Pet ID: ");
            int petId = Integer.parseInt(scanner.nextLine());

            System.out.print("Medication Name: ");
            String name = scanner.nextLine();

            System.out.print("Dosage: ");
            String dosage = scanner.nextLine();

            System.out.print("Frequency: ");
            String frequency = scanner.nextLine();

            System.out.print("Prescribing Veterinarian: ");
            String vet = scanner.nextLine();

            String sql = "INSERT INTO prescriptions (pet_id, medication_name, dosage, frequency, prescribing_vet) " +
                         "VALUES (?, ?, ?, ?, ?);";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, petId);
                ps.setString(2, name);
                ps.setString(3, dosage);
                ps.setString(4, frequency);
                ps.setString(5, vet);

                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Prescription added." : "Failed to add prescription.");
            }

        } catch (SQLException e) {
            System.err.println("Error adding prescription: " + e.getMessage());
        }
    }

    // ========================= VIEW ===========================
    public void view() {
        System.out.println("--- View All Prescriptions ---");

        String sql = "SELECT * FROM prescriptions;";

        try (Connection conn = dbHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;
            while (rs.next()) {
                System.out.printf("ID: %d | Pet ID: %d | Medication: %s | Dosage: %s | Frequency: %s | Vet: %s%n",
                        rs.getInt("prescription_id"),
                        rs.getInt("pet_id"),
                        rs.getString("medication_name"),
                        rs.getString("dosage"),
                        rs.getString("frequency"),
                        rs.getString("prescribing_vet"));
                found = true;
            }

            if (!found) System.out.println("No prescriptions found.");

        } catch (SQLException e) {
            System.err.println("Error retrieving prescriptions: " + e.getMessage());
        }
    }

    // ========================= UPDATE ===========================
    public void update() {
        try (Connection conn = dbHelper.getConnection()) {

            System.out.print("Enter Prescription ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            String selectSQL = "SELECT * FROM prescriptions WHERE prescription_id = ?;";
            try (PreparedStatement select = conn.prepareStatement(selectSQL)) {
                select.setInt(1, id);
                ResultSet rs = select.executeQuery();

                if (!rs.next()) {
                    System.out.println("No such prescription found.");
                    return;
                }

                String currentName = rs.getString("medication_name");
                String currentDosage = rs.getString("dosage");
                String currentFrequency = rs.getString("frequency");
                String currentVet = rs.getString("prescribing_vet");

                System.out.println("--- Leave fields blank to keep current values ---");

                System.out.print("Medication Name [" + currentName + "]: ");
                String name = scanner.nextLine();
                if (name.isEmpty()) name = currentName;

                System.out.print("Dosage [" + currentDosage + "]: ");
                String dosage = scanner.nextLine();
                if (dosage.isEmpty()) dosage = currentDosage;

                System.out.print("Frequency [" + currentFrequency + "]: ");
                String frequency = scanner.nextLine();
                if (frequency.isEmpty()) frequency = currentFrequency;

                System.out.print("Prescribing Vet [" + currentVet + "]: ");
                String vet = scanner.nextLine();
                if (vet.isEmpty()) vet = currentVet;

                String updateSQL = "UPDATE prescriptions SET " +
                        "medication_name = ?, dosage = ?, frequency = ?, prescribing_vet = ? " +
                        "WHERE prescription_id = ?;";

                try (PreparedStatement update = conn.prepareStatement(updateSQL)) {
                    update.setString(1, name);
                    update.setString(2, dosage);
                    update.setString(3, frequency);
                    update.setString(4, vet);
                    update.setInt(5, id);

                    int rows = update.executeUpdate();
                    System.out.println(rows > 0 ? "Prescription updated." : "Update failed.");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error updating prescription: " + e.getMessage());
        }
    }

    // ========================= DELETE ===========================
    public void delete() {
        try (Connection conn = dbHelper.getConnection()) {

            System.out.print("Enter Prescription ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            String sql = "DELETE FROM prescriptions WHERE prescription_id = ?;";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Prescription deleted." : "No such prescription ID.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting prescription: " + e.getMessage());
        }
    }
}