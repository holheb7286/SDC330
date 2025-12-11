/*******************************************************************
 * Name: Holly Hebert
 * Date: December 10, 2025
 * Assignment: SDC330 Week 5 – Final Project
 * Class: EmergencyContactManager
 *
 * Description:
 * Provides full CRUD functionality for managing emergency contact
 * information associated with pets. Uses SQLite for persistent storage
 * and allows editing of specific fields without requiring all values.
 *******************************************************************/

import java.sql.*;
import java.util.Scanner;

public class EmergencyContactManager {

    private final DatabaseHelper dbHelper;
    private final Scanner scanner;

    public EmergencyContactManager(DatabaseHelper dbHelper, Scanner scanner) {
        this.dbHelper = dbHelper;
        this.scanner = scanner;
    }

    // ========================= ADD ===========================
    public void add() {
        try (Connection conn = dbHelper.getConnection()) {

            System.out.println("--- Add Emergency Contact ---");

            System.out.print("Pet ID: ");
            int petId = Integer.parseInt(scanner.nextLine());

            System.out.print("Contact Name: ");
            String name = scanner.nextLine();

            System.out.print("Relationship: ");
            String relationship = scanner.nextLine();

            System.out.print("Phone Number: ");
            String phone = scanner.nextLine();

            String sql = "INSERT INTO emergency_contacts(pet_id, name, relationship, phone) VALUES (?, ?, ?, ?);";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, petId);
                ps.setString(2, name);
                ps.setString(3, relationship);
                ps.setString(4, phone);

                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Contact added." : "Failed to add contact.");
            }

        } catch (Exception e) {
            System.err.println("Error adding contact: " + e.getMessage());
        }
    }

    // ========================= VIEW ===========================
    public void view() {
        System.out.println("--- View All Emergency Contacts ---");

        String sql = "SELECT * FROM emergency_contacts;";

        try (Connection conn = dbHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;
            while (rs.next()) {
                int id = rs.getInt("contact_id");
                int petId = rs.getInt("pet_id");
                String name = rs.getString("name");
                String relationship = rs.getString("relationship");
                String phone = rs.getString("phone");

                System.out.printf("ID: %d | Pet ID: %d | Name: %s | Relationship: %s | Phone: %s%n",
                        id, petId, name, relationship, phone);
                found = true;
            }

            if (!found) {
                System.out.println("No emergency contacts found.");
            }

        } catch (Exception e) {
            System.err.println("Error viewing contacts: " + e.getMessage());
        }
    }

    // ========================= UPDATE ===========================
    public void update() {
        try (Connection conn = dbHelper.getConnection()) {

            System.out.print("Enter Contact ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.println("--- Leave any field blank to keep current value ---");

            System.out.print("New Name: ");
            String name = scanner.nextLine();

            System.out.print("New Relationship: ");
            String relationship = scanner.nextLine();

            System.out.print("New Phone: ");
            String phone = scanner.nextLine();

            String sql = "UPDATE emergency_contacts SET "
                       + "name = COALESCE(NULLIF(?, ''), name), "
                       + "relationship = COALESCE(NULLIF(?, ''), relationship), "
                       + "phone = COALESCE(NULLIF(?, ''), phone) "
                       + "WHERE contact_id = ?;";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, relationship);
                ps.setString(3, phone);
                ps.setInt(4, id);

                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Contact updated." : "No such contact.");
            }

        } catch (Exception e) {
            System.err.println("Error updating contact: " + e.getMessage());
        }
    }

    // ========================= DELETE ===========================
    public void delete() {
        try (Connection conn = dbHelper.getConnection()) {

            System.out.print("Enter Contact ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            String sql = "DELETE FROM emergency_contacts WHERE contact_id = ?;";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Contact deleted." : "Contact not found.");
            }

        } catch (Exception e) {
            System.err.println("Error deleting contact: " + e.getMessage());
        }
    }
}