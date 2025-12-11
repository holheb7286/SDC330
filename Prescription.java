/*******************************************************************
 * Name: Holly Hebert
 * Date: December 10, 2025
 * Assignment: SDC330 Week 5 – Final Project
 * Class: Prescription
 *
 * Description:
 * Represents a prescription entry for a specific pet, containing 
 * medication name, dosage, frequency, and prescribing vet.
 * Supports full object construction and formatted display.
 *******************************************************************/

public class Prescription {

    private int prescriptionId;
    private int petId;
    private String medicationName;
    private String dosage;
    private String frequency;
    private String prescribingVet;

    // Full constructor
    public Prescription(int prescriptionId, int petId, String medicationName,
                        String dosage, String frequency, String prescribingVet) {
        this.prescriptionId = prescriptionId;
        this.petId = petId;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.prescribingVet = prescribingVet;
    }

    // Constructor without prescriptionId (for inserts)
    public Prescription(int petId, String medicationName,
                        String dosage, String frequency, String prescribingVet) {
        this.petId = petId;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.prescribingVet = prescribingVet;
    }

    // Getters and setters
    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getPrescribingVet() {
        return prescribingVet;
    }

    public void setPrescribingVet(String prescribingVet) {
        this.prescribingVet = prescribingVet;
    }

    // Formatted display
    public String getPrescriptionDetails() {
        return "Prescription #" + prescriptionId +
               "\nPet ID: " + petId +
               "\nMedication: " + medicationName +
               "\nDosage: " + dosage +
               "\nFrequency: " + frequency +
               "\nPrescribed By: " + prescribingVet + "\n";
    }
}