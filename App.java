/*******************************************************************
 * Name: Holly Hebert
 * Date: December 5, 2025
 * Assignment: SDC330 Week 4 – App Entry Point
 * Class: App.java
 *
 * Description:
 * - Entry point of the Animal Health Checker.
 * - Demonstrates a working menu system with real SQLite database
 *   integration for managing pets.
 *******************************************************************/

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("    Animal Health Checker - Week 4");
        System.out.println("           By: Holly Hebert");
        System.out.println("===========================================\n");

        DatabaseHelper dbHelper = new DatabaseHelper();
        PetManager manager = new PetManager(dbHelper);
        Scanner scanner = new Scanner(System.in);  // Don't close this
        boolean quit = false;

        while (!quit) {
            System.out.println("\n--- Pet Management Menu ---");
            System.out.println("1. Add Pet");
            System.out.println("2. View All Pets");
            System.out.println("3. Update Pet Info");
            System.out.println("4. Delete Pet");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manager.add();
                    break;
                case "2":
                    manager.view();
                    break;
                case "3":
                    manager.update();
                    break;
                case "4":
                    manager.delete();
                    break;
                case "5":
                    quit = true;
                    System.out.println("Goodbye, and give your pets a treat for me!");
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number from 1 to 5.");
            }
        }
        scanner.close();
    }
}