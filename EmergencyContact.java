public class EmergencyContact {

    private int contactId;
    private int petId;
    private String name;
    private String phone;
    private String relationship;

    public EmergencyContact(int contactId, int petId, String name,
                            String phone, String relationship) {
        this.contactId = contactId;
        this.petId = petId;
        this.name = name;
        this.phone = phone;
        this.relationship = relationship;
    }

    public String getContactDetails() {
        return "Emergency Contact #" + contactId +
               "\nPet ID: " + petId + "\nName: " + name +
               "\nPhone: " + phone + "\nRelationship: " + relationship + "\n";
    }
}