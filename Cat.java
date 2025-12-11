/*******************************************************************
 * Name: Holly Hebert
 * Date: December 10, 2025
 * Assignment: SDC330 Week 5 – Final Project
 * Class: Cat
 *
 * Description:
 * This class represents a Cat, extending the abstract Pet class.
 * Demonstrates inheritance and polymorphism. Includes additional
 * attribute for declawed status and overrides getProfile().
 *******************************************************************/

public class Cat extends Pet {

    private boolean declawed;

    // Constructor
    public Cat(int petId, String name, String breed, int age, double weight,
               String dietaryNotes, String behaviorNotes, boolean declawed) {
        super(petId, name, "Cat", breed, age, weight, dietaryNotes, behaviorNotes);
        this.declawed = declawed;
    }

    // Getter and Setter
    public boolean isDeclawed() {
        return declawed;
    }

    public void setDeclawed(boolean declawed) {
        this.declawed = declawed;
    }

    // Override getProfile to include cat-specific details
    @Override
    public String getProfile() {
        return "Cat Profile:\n" +
               "ID: " + getPetId() + "\n" +
               "Name: " + getName() + "\n" +
               "Breed: " + getBreed() + "\n" +
               "Age: " + getAge() + " years\n" +
               "Weight: " + getWeight() + " lbs\n" +
               "Declawed: " + (declawed ? "Yes" : "No") + "\n" +
               "Dietary Notes: " + getDietaryNotes() + "\n" +
               "Behavior Notes: " + getBehaviorNotes() + "\n";
    }
}