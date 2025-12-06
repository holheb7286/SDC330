public class Appointment {

    private int appointmentId;
    private int petId;
    private String date;
    private String time;
    private String type;
    private String notes;

    public Appointment(int appointmentId, int petId, String date, String time,
                       String type, String notes) {
        this.appointmentId = appointmentId;
        this.petId = petId;
        this.date = date;
        this.time = time;
        this.type = type;
        this.notes = notes;
    }

    public String getAppointmentDetails() {
        return "Appointment #" + appointmentId +
               "\nPet ID: " + petId + "\nDate: " + date +
               "\nTime: " + time + "\nType: " + type +
               "\nNotes: " + notes + "\n";
    }
}