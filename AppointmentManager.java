/*******************************************************************
 * Name: Holly Hebert
 * Date: December 10, 2025
 * Assignment: SDC330 Week 5 – Final Project
 * Class: AppointmentManager
 *
 * Description:
 * Provides full CRUD functionality for managing pet appointment
 * records stored in the SQLite database. Integrates with the
 * shared DatabaseHelper to execute SQL queries.
 *******************************************************************/

import java.sql.*;
import java.util.Scanner;

public class AppointmentManager {

    private final DatabaseHelper dbHelper;
    private final Scanner scanner;

    public AppointmentManager(DatabaseHelper dbHelper, Scanner scanner) {
        this.dbHelper = dbHelper;
        this.scanner = scanner;
    }

    // ======================== ADD ===========================
    public void add() {
        try (Connection conn = dbHelper.getConnection()) {

            System.out.println("--- Add Appointment ---");

            System.out.print("Pet ID: ");
            int petId = Integer.parseInt(scanner.nextLine());

            System.out.print("Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            System.out.print("Time (HH:MM): ");
            String time = scanner.nextLine();

            System.out.print("Reason: ");
            String reason = scanner.nextLine();

            String sql = "INSERT INTO appointments(pet_id, date, time, reason) VALUES (?, ?, ?, ?);";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, petId);
                ps.setString(2, date);
                ps.setString(3, time);
                ps.setString(4, reason);

                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Appointment added." : "Failed to add appointment.");
            }

        } catch (Exception e) {
            System.err.println("Error adding appointment: " + e.getMessage());
        }
    }

    // ======================== VIEW ===========================
    public void view() {
        System.out.println("--- View All Appointments ---");

        String sql = "SELECT * FROM appointments;";

        try (Connection conn = dbHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;
            while (rs.next()) {
                int id = rs.getInt("appointment_id");
                int petId = rs.getInt("pet_id");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String reason = rs.getString("reason");

                System.out.printf("ID: %d | Pet ID: %d | Date: %s | Time: %s | Reason: %s%n",
                        id, petId, date, time, reason);
                found = true;
            }

            if (!found) {
                System.out.println("No appointments found.");
            }

        } catch (Exception e) {
            System.err.println("Error viewing appointments: " + e.getMessage());
        }
    }

    // ======================== UPDATE ===========================
    public void update() {
        try (Connection conn = dbHelper.getConnection()) {

            System.out.print("Enter Appointment ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.println("--- Leave any field blank to keep current value ---");

            System.out.print("New Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            System.out.print("New Time (HH:MM): ");
            String time = scanner.nextLine();

            System.out.print("New Reason: ");
            String reason = scanner.nextLine();

            String sql = "UPDATE appointments SET "
                       + "date = COALESCE(NULLIF(?, ''), date), "
                       + "time = COALESCE(NULLIF(?, ''), time), "
                       + "reason = COALESCE(NULLIF(?, ''), reason) "
                       + "WHERE appointment_id = ?;";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, date);
                ps.setString(2, time);
                ps.setString(3, reason);
                ps.setInt(4, id);

                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Appointment updated." : "No matching appointment found.");
            }

        } catch (Exception e) {
            System.err.println("Error updating appointment: " + e.getMessage());
        }
    }

    // ======================== DELETE ===========================
    public void delete() {
        try (Connection conn = dbHelper.getConnection()) {

            System.out.print("Enter Appointment ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            String sql = "DELETE FROM appointments WHERE appointment_id = ?;";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Appointment deleted." : "No such appointment.");
            }

        } catch (Exception e) {
            System.err.println("Error deleting appointment: " + e.getMessage());
        }
    }
}