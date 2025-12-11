/*******************************************************************
 * Name: Holly Hebert
 * Date: December 10, 2025
 * Assignment: SDC330 Week 5 – Final Project
 * Class: MedicalRecordManager
 *
 * Description:
 * This class manages pet medical records stored in an SQLite
 * database. Provides full CRUD functionality, including selective
 * field updates for editing. Follows the same structure as PetManager.
 *******************************************************************/

import java.sql.*;
import java.util.Scanner;

public class MedicalRecordManager {

    private final DatabaseHelper dbHelper;
    private final Scanner scanner;

    public MedicalRecordManager(DatabaseHelper dbHelper, Scanner scanner) {
        this.dbHelper = dbHelper;
        this.scanner = scanner;
    }

    public void add() {
        try (Connection conn = dbHelper.getConnection()) {

            System.out.println("--- Add Medical Record ---");

            System.out.print("Pet ID: ");
            int petId = Integer.parseInt(scanner.nextLine());

            System.out.print("Condition: ");
            String condition = scanner.nextLine();

            System.out.print("Treatment: ");
            String treatment = scanner.nextLine();

            System.out.print("Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            String sql = "INSERT INTO medical_records(pet_id, condition, treatment, date) VALUES (?, ?, ?, ?);";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, petId);
                ps.setString(2, condition);
                ps.setString(3, treatment);
                ps.setString(4, date);

                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Medical record added." : "Failed to add record.");
            }

        } catch (Exception e) {
            System.err.println("Error adding medical record: " + e.getMessage());
        }
    }

    public void view() {
        System.out.println("--- View All Medical Records ---");
        String sql = "SELECT * FROM medical_records;";

        try (Connection conn = dbHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("record_id");
                int petId = rs.getInt("pet_id");
                String condition = rs.getString("condition");
                String treatment = rs.getString("treatment");
                String date = rs.getString("date");

                System.out.printf("ID: %d | Pet ID: %d | Condition: %s | Treatment: %s | Date: %s%n",
                        id, petId, condition, treatment, date);
            }

        } catch (Exception e) {
            System.err.println("Error viewing records: " + e.getMessage());
        }
    }

    public void update() {
        try (Connection conn = dbHelper.getConnection()) {

            System.out.print("Enter Record ID to update: ");
            int recordId = Integer.parseInt(scanner.nextLine());

            System.out.println("--- Leave fields blank to retain existing values ---");

            System.out.print("New Condition: ");
            String condition = scanner.nextLine();

            System.out.print("New Treatment: ");
            String treatment = scanner.nextLine();

            System.out.print("New Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            String sql = "UPDATE medical_records SET "
                       + "condition = COALESCE(NULLIF(?, ''), condition), "
                       + "treatment = COALESCE(NULLIF(?, ''), treatment), "
                       + "date = COALESCE(NULLIF(?, ''), date) "
                       + "WHERE record_id = ?;";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, condition);
                ps.setString(2, treatment);
                ps.setString(3, date);
                ps.setInt(4, recordId);

                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Record updated." : "No record found.");
            }

        } catch (Exception e) {
            System.err.println("Error updating record: " + e.getMessage());
        }
    }

    public void delete() {
        try (Connection conn = dbHelper.getConnection()) {

            System.out.print("Enter Record ID to delete: ");
            int recordId = Integer.parseInt(scanner.nextLine());

            String sql = "DELETE FROM medical_records WHERE record_id = ?;";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, recordId);
                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Record deleted." : "No such record.");
            }

        } catch (Exception e) {
            System.err.println("Error deleting record: " + e.getMessage());
        }
    }
}