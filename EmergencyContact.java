/*******************************************************************
 * Name: Holly Hebert
 * Date: December 10, 2025
 * Assignment: SDC330 Week 5 – Final Project
 * Class: EmergencyContact
 *
 * Description:
 * Represents an emergency contact linked to a specific pet.
 * Stores name, phone number, and relationship details.
 * Used in conjunction with database records and view logic.
 *******************************************************************/

public class EmergencyContact {

    private int contactId;
    private int petId;
    private String name;
    private String phone;
    private String relationship;

    // Full constructor
    public EmergencyContact(int contactId, int petId, String name,
                            String phone, String relationship) {
        this.contactId = contactId;
        this.petId = petId;
        this.name = name;
        this.phone = phone;
        this.relationship = relationship;
    }

    // Getters (if needed later for dynamic views or updates)
    public int getContactId() {
        return contactId;
    }

    public int getPetId() {
        return petId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getRelationship() {
        return relationship;
    }

    public String getContactDetails() {
        return "Emergency Contact #" + contactId +
               "\nPet ID: " + petId + "\nName: " + name +
               "\nPhone: " + phone + "\nRelationship: " + relationship + "\n";
    }
}