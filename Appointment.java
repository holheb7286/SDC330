/*******************************************************************
 * Name: Holly Hebert
 * Date: December 10, 2025
 * Assignment: SDC330 Final Project – Pet Health Manager
 * Class: Appointment.java
 *
 * Description:
 * - Represents an appointment associated with a pet.
 * - Stores date, time, type, and any additional notes.
 *******************************************************************/

public class Appointment {

    private int appointmentId;
    private int petId;
    private String date;
    private String time;
    private String type;
    private String notes;

    // Full constructor (used when reading from database)
    public Appointment(int appointmentId, int petId, String date, String time,
                       String type, String notes) {
        this.appointmentId = appointmentId;
        this.petId = petId;
        this.date = date;
        this.time = time;
        this.type = type;
        this.notes = notes;
    }

    // Constructor without appointmentId (used when inserting new records)
    public Appointment(int petId, String date, String time,
                       String type, String notes) {
        this.petId = petId;
        this.date = date;
        this.time = time;
        this.type = type;
        this.notes = notes;
    }

    // Getters and setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAppointmentDetails() {
        return "Appointment #" + appointmentId +
               "\nPet ID: " + petId +
               "\nDate: " + date +
               "\nTime: " + time +
               "\nType: " + type +
               "\nNotes: " + notes + "\n";
    }
}