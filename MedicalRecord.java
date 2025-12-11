/*******************************************************************
 * Name: Holly Hebert
 * Date: December 10, 2025
 * Assignment: SDC330 Week 5 – Final Project
 * Class: MedicalRecord
 *
 * Description:
 * Represents a single medical entry for a pet.
 * Demonstrates composition (Pet has multiple MedicalRecords).
 *******************************************************************/

public class MedicalRecord {

    private int recordId;
    private int petId;
    private String condition;
    private String treatment;
    private String veterinarian;
    private String dateOfVisit;
    private String vaccinationStatus;

    // Full constructor
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

    // --- Getters ---
    public int getRecordId() {
        return recordId;
    }

    public int getPetId() {
        return petId;
    }

    public String getCondition() {
        return condition;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public String getDateOfVisit() {
        return dateOfVisit;
    }

    public String getVaccinationStatus() {
        return vaccinationStatus;
    }

    // --- Setters ---
    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public void setVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }

    public void setDateOfVisit(String dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public void setVaccinationStatus(String vaccinationStatus) {
        this.vaccinationStatus = vaccinationStatus;
    }

    // --- Display Method ---
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