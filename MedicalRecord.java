/*
 * Represents a single medical entry for a pet.
 * Demonstrates composition (Pet has multiple MedicalRecords).
 */

public class MedicalRecord {

    private int recordId;
    private int petId;
    private String condition;
    private String treatment;
    private String veterinarian;
    private String dateOfVisit;
    private String vaccinationStatus;

    public MedicalRecord(int recordId, int petId, String condition, String treatment, 
                         String veterinarian, String dateOfVisit, String vaccinationStatus) {
        this.recordId = recordId;
        this.petId = petId;
        this.condition = condition;
        this.treatment = treatment;
        this.veterinarian = veterinarian;
        this.dateOfVisit = dateOfVisit;
        this.vaccinationStatus = vaccinationStatus;
    }

    public String getRecordDetails() {
        return "Medical Record #" + recordId +
               "\nPet ID: " + petId +
               "\nCondition: " + condition +
               "\nTreatment: " + treatment +
               "\nVeterinarian: " + veterinarian +
               "\nDate: " + dateOfVisit +
               "\nVaccination Status: " + vaccinationStatus + "\n";
    }
}