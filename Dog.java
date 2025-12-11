/*******************************************************************
 * Name: Holly Hebert
 * Date: December 10, 2025
 * Assignment: SDC330 Week 5 – Final Project
 * Class: Dog
 *
 * Description:
 * Represents a Dog subclass of Pet.
 * Demonstrates inheritance and polymorphism.
 * Adds training level as a unique attribute.
 *******************************************************************/

public class Dog extends Pet {

    private String trainingLevel;

    public Dog(int petId, String name, String breed, int age, double weight,
               String dietaryNotes, String behaviorNotes, String trainingLevel) {
        super(petId, name, "Dog", breed, age, weight, dietaryNotes, behaviorNotes);
        this.trainingLevel = trainingLevel;
    }

    public String getTrainingLevel() {
        return trainingLevel;
    }

    public void setTrainingLevel(String trainingLevel) {
        this.trainingLevel = trainingLevel;
    }

    @Override
    public String getProfile() {
        return "Dog Profile:\n" +
               "ID: " + petId + "\nName: " + name + "\nBreed: " + breed +
               "\nAge: " + age + " years\nWeight: " + weight + " lbs\n" +
               "Training Level: " + trainingLevel + "\nDietary Notes: " + dietaryNotes +
               "\nBehavior Notes: " + behaviorNotes + "\n";
    }
}