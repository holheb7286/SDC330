/*******************************************************************
 * Name: Holly Hebert
 * Date: December 1, 2025
 * Assignment: SDC330 Week 3 – Abstraction, Constructors, Access Specifiers
 * Class: PetHealthAppWeek3 (Demonstration Application)
 * Description:
 * This simple console application demonstrates the use of abstraction,
 * constructors, access specifiers, composition, and polymorphism within
 * the Pet Health Management System. A small menu is provided to preview
 * future application behavior while keeping functionality appropriate
 * for Week 3 (no database operations yet).
 *******************************************************************/

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PetManager manager = new PetManager();

        // ------------------------------------------------------------
        // Sample Data (demonstrates constructors, abstraction, and comp.)
        // ------------------------------------------------------------
        Pet dog = new Dog(
            1, "Buddy", "Labrador", 5, 72.5,
            "Grain-free diet", "Very social", "Intermediate"
        );

        Pet cat = new Cat(
            2, "Misty", "Ragdoll", 3, 12.3,
            "High-protein diet", "Likes quiet spaces", false
        );

        MedicalRecord record = new MedicalRecord(
            101, 1, "Skin Allergy", "Antihistamine injection",
            "Dr. Nolan", "11/28/2025", "Up to date"
        );

        Appointment appointment = new Appointment(
            2001, 2, "12/05/2025", "3:00 PM", "Wellness Check",
            "Annual exam with mild anxiety noted."
        );

        Prescription prescription = new Prescription(
            5001, 1, "Omega-3 Supplement", "2 capsules", "Daily",
            "Dr. Nolan"
        );

        EmergencyContact contact = new EmergencyContact(
            3001, 1, "Sarah Collins", "555-9090", "Owner’s Sister"
        );

        // ------------------------------------------------------------
        // Application Header
        // ------------------------------------------------------------
        System.out.println("==============================================");
        System.out.println("   Week 3 – Pet Health System Demonstration   ");
        System.out.println("               By: Holly Hebert              ");
        System.out.println("==============================================\n");

        System.out.println("Welcome! This mini-menu previews the structure of the final");
        System.out.println("Pet Health Management System. For Week 3, this demo shows:");
        System.out.println("- Abstraction and polymorphism via the Pet hierarchy");
        System.out.println("- Constructors and constructor overloading");
        System.out.println("- Access specifiers and encapsulation");
        System.out.println("- Composition with medical records, prescriptions, etc.\n");

        int choice;

        do {
            System.out.println("\n---------- MAIN MENU ----------");
            System.out.println("1. View Sample Pet Profiles");
            System.out.println("2. View Sample Medical Record");
            System.out.println("3. View Sample Appointment");
            System.out.println("4. View Sample Prescription");
            System.out.println("5. View Emergency Contact");
            System.out.println("6. Run Manager Stub (Week 4 Preview)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("\n--- Pet Profiles (Abstraction + Polymorphism) ---");
                    System.out.println(dog.getProfile());
                    System.out.println(cat.getProfile());
                    break;

                case 2:
                    System.out.println("\n--- Medical Record (Composition) ---");
                    System.out.println(record.getRecordDetails());
                    break;

                case 3:
                    System.out.println("\n--- Appointment Details ---");
                    System.out.println(appointment.getAppointmentDetails());
                    break;

                case 4:
                    System.out.println("\n--- Prescription Details ---");
                    System.out.println(prescription.getPrescriptionDetails());
                    break;

                case 5:
                    System.out.println("\n--- Emergency Contact ---");
                    System.out.println(contact.getContactDetails());
                    break;

                case 6:
                    System.out.println("\n--- Manager Stub Demonstration ---");
                    manager.add();
                    manager.update();
                    manager.delete();
                    manager.view();
                    break;

                case 7:
                    System.out.println("\nGoodbye! Week 4 awaits…");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (choice != 7);

        scanner.close();
    }
}
