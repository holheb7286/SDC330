/*******************************************************************
 * Name: Holly Hebert
 * Date: December 10, 2025
 * Assignment: SDC330 Final Project – Pet Health Manager
 * Class: App.java
 *
 * Description:
 * Entry point of the Animal Health Checker application.
 * Demonstrates a working menu system with SQLite database
 * integration for managing pets, medical records, prescriptions,
 * appointments, and emergency contacts.
 *******************************************************************/

import java.util.Scanner;
import java.sql.*;

public class App {

    private static final Scanner scanner = new Scanner(System.in); // shared + not closed

    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("    Animal Health Checker - Final Project");
        System.out.println("           By: Holly Hebert");
        System.out.println("===========================================\n");

        DatabaseHelper dbHelper = new DatabaseHelper();

        PetManager petManager = new PetManager(dbHelper);
        MedicalRecordManager medManager = new MedicalRecordManager(dbHelper, scanner);
        AppointmentManager apptManager = new AppointmentManager(dbHelper, scanner);
        PrescriptionManager prescManager = new PrescriptionManager(dbHelper, scanner);
        EmergencyContactManager contactManager = new EmergencyContactManager(dbHelper, scanner);

        boolean quit = false;

        while (!quit) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Add Pet");
            System.out.println("2. View All Pets");
            System.out.println("3. Update Pet Info");
            System.out.println("4. Delete Pet");
            System.out.println("5. Add Medical Record");
            System.out.println("6. View Medical Records");
            System.out.println("7. Update Medical Record");
            System.out.println("8. Delete Medical Record");
            System.out.println("9. Add Appointment");
            System.out.println("10. View Appointments");
            System.out.println("11. Update Appointment");
            System.out.println("12. Delete Appointment");
            System.out.println("13. Add Prescription");
            System.out.println("14. View Prescriptions");
            System.out.println("15. Update Prescription");
            System.out.println("16. Delete Prescription");
            System.out.println("17. Add Emergency Contact");
            System.out.println("18. View Emergency Contacts");
            System.out.println("19. Update Emergency Contact");
            System.out.println("20. Delete Emergency Contact");
            System.out.println("21. View Full Pet Profile");
            System.out.println("22. Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> petManager.add();
                case "2" -> petManager.view();
                case "3" -> petManager.update();
                case "4" -> petManager.delete();
                case "5" -> medManager.add();
                case "6" -> medManager.view();
                case "7" -> medManager.update();
                case "8" -> medManager.delete();
                case "9" -> apptManager.add();
                case "10" -> apptManager.view();
                case "11" -> apptManager.update();
                case "12" -> apptManager.delete();
                case "13" -> prescManager.add();
                case "14" -> prescManager.view();
                case "15" -> prescManager.update();
                case "16" -> prescManager.delete();
                case "17" -> contactManager.add();
                case "18" -> contactManager.view();
                case "19" -> contactManager.update();
                case "20" -> contactManager.delete();
                case "21" -> viewFullPetProfile(dbHelper);
                case "22" -> {
                    quit = true;
                    System.out.println("Goodbye, and give your pets a treat for me!");
                }
                default -> System.out.println("Invalid option. Please enter a number from 1 to 22.");
            }
        }
    }

    // ---------------- VIEW FULL PROFILE --------------------
    private static void viewFullPetProfile(DatabaseHelper dbHelper) {
        System.out.print("Enter Pet ID to view full profile: ");
        int petId = Integer.parseInt(scanner.nextLine());

        try (Connection conn = dbHelper.getConnection()) {

            // Pet Info
            String petSql = "SELECT * FROM pets WHERE pet_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(petSql)) {
                ps.setInt(1, petId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println("\n--- Pet Info ---");
                    System.out.printf("ID: %d | Name: %s | Species: %s | Breed: %s | Age: %d | Weight: %.2f lbs\n",
                            rs.getInt("pet_id"),
                            rs.getString("name"),
                            rs.getString("species"),
                            rs.getString("breed"),
                            rs.getInt("age"),
                            rs.getDouble("weight"));
                } else {
                    System.out.println("Pet not found.");
                    return;
                }
            }

            // Medical Records
            System.out.println("\n--- Medical Records ---");
            String medSql = "SELECT * FROM medical_records WHERE pet_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(medSql)) {
                ps.setInt(1, petId);
                ResultSet rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) System.out.println("No medical records.");
                while (rs.next()) {
                    System.out.printf("Record #%d | Condition: %s | Treatment: %s | Date: %s\n",
                            rs.getInt("record_id"),
                            rs.getString("condition"),
                            rs.getString("treatment"),
                            rs.getString("date"));
                }
            }

            // Appointments
            System.out.println("\n--- Appointments ---");
            String apptSql = "SELECT * FROM appointments WHERE pet_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(apptSql)) {
                ps.setInt(1, petId);
                ResultSet rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) System.out.println("No appointments.");
                while (rs.next()) {
                    System.out.printf("Appointment #%d | Date: %s | Time: %s | Reason: %s\n",
                            rs.getInt("appointment_id"),
                            rs.getString("date"),
                            rs.getString("time"),
                            rs.getString("reason"));
                }
            }

            // Prescriptions
            System.out.println("\n--- Prescriptions ---");
            String presSql = "SELECT * FROM prescriptions WHERE pet_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(presSql)) {
                ps.setInt(1, petId);
                ResultSet rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) System.out.println("No prescriptions.");
                while (rs.next()) {
                    System.out.printf("Prescription #%d | Medication: %s | Dosage: %s | Frequency: %s | Vet: %s\n",
                            rs.getInt("prescription_id"),
                            rs.getString("medication_name"),
                            rs.getString("dosage"),
                            rs.getString("frequency"),
                            rs.getString("prescribing_vet"));
                }
            }

            // Emergency Contacts
            System.out.println("\n--- Emergency Contacts ---");
            String contactSql = "SELECT * FROM emergency_contacts WHERE pet_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(contactSql)) {
                ps.setInt(1, petId);
                ResultSet rs = ps.executeQuery();
                if (!rs.isBeforeFirst()) System.out.println("No emergency contacts.");
                while (rs.next()) {
                    System.out.printf("Contact #%d | Name: %s | Phone: %s | Relationship: %s\n",
                            rs.getInt("contact_id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("relationship"));
                }
            }

        } catch (Exception e) {
            System.err.println("Error displaying profile: " + e.getMessage());
        }
    }
}