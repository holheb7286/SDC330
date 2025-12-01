public class Prescription {

    private int prescriptionId;
    private int petId;
    private String medicationName;
    private String dosage;
    private String frequency;
    private String prescribingVet;

    public Prescription(int prescriptionId, int petId, String medicationName,
                        String dosage, String frequency, String prescribingVet) {
        this.prescriptionId = prescriptionId;
        this.petId = petId;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.prescribingVet = prescribingVet;
    }

    public String getPrescriptionDetails() {
        return "Prescription #" + prescriptionId +
               "\nPet ID: " + petId + "\nMedication: " + medicationName +
               "\nDosage: " + dosage + "\nFrequency: " + frequency +
               "\nPrescribed By: " + prescribingVet + "\n";
    }
}