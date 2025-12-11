Project Name: Pet Health Manager

Project Description
Pet Health Manager is a console-based Java application designed to provide veterinary staff or 
pet owners with a comprehensive tool for managing animal health records. The system allows users to 
track and update pet information, medical history, appointments, prescriptions, and emergency 
contacts, all backed by a fully integrated SQLite database. The design prioritizes clarity, functionality, 
and real-world usability, demonstrating key software development principles such as object-oriented programming,
abstraction, inheritance, composition, and polymorphism.

Project Tasks
•	Task 1: Set up the development environment
o	Installed JDK and configured Java in Visual Studio Code
o	Set up GitHub repository and initialized project structure

•	Task 2: Design application architecture
o	Identified classes and database schema
o	Designed object-oriented relationships (e.g., Pet, Cat, Dog, Appointment, Prescription)

•	Task 3: Build database helper and schema
o	Created DatabaseHelper.java to manage SQLite connection
o	Implemented logic to auto-create tables if they don’t exist

•	Task 4: Develop core classes and models
o	Implemented abstract class Pet and extended it into Cat and Dog
o	Added POJO classes for Appointment, MedicalRecord, Prescription, and EmergencyContact

•	Task 5: Build manager classes for CRUD functionality
o	Created PetManager, MedicalRecordManager, AppointmentManager, EmergencyContactManager, and PrescriptionManager
o	Ensured all managers followed a consistent CRUD structure and interface (Manageable)

•	Task 6: Implement main menu and user interaction
o	Designed intuitive menu system in App.java
o	Integrated shared Scanner object to streamline input without closure issues

•	Task 7: Integrate full pet profile reports
o	Added functionality to display full profile of a pet, including all related records across multiple tables

•	Task 8: Debug and test all features
o	Handled SQL exceptions and user input errors
o	Verified database persistence and correctness of CRUD operations

•	Task 9: Document and finalize project
o	Added header comments to every file for instructor clarity
o	Ensured code readability, consistency, and formatting

Project Skills Learned
•	Object-oriented programming in Java
•	Working with SQLite databases and JDBC
•	Abstraction, inheritance, polymorphism, and composition
•	CRUD implementation and input validation
•	Console-based user interface design
•	Error handling and debugging techniques
•	Code documentation and commenting
•	Project structuring and source control with Git

Language Used
•	Java: For all application logic and interface
•	SQL (SQLite): For persistent storage and schema creation

Development Process Used
•	Iterative Development: Frequent testing, refining, and expanding functionality based on instructor criteria
•	Hands-on Debugging: Real-time error solving and feature upgrades
•	Code Review Style: Organized, commented, and styled code for clarity and professionalism

Notes
•	The program initializes the database schema automatically upon startup
•	All CRUD functionality is accessible via the menu
•	A shared Scanner is used across managers to prevent input stream errors
•	Full pet profiles consolidate all relevant records (appointments, prescriptions, contacts, etc.)

Link to Project
https://github.com/holheb7286/SDC330
