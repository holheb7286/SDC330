/*******************************************************************
 * Name: Holly Hebert
 * Date: December 6, 2025
 * Assignment: SDC330 Week 3 – Class Implementation
 * Class: Pet (Abstract)
 *
 * Description:
 * Abstract base class representing a general pet profile.
 * Demonstrates abstraction, inheritance, and polymorphism
 * (via getProfile()). Used by Dog and Cat subclasses.
 *******************************************************************/

public abstract class Pet {

    protected int petId;
    protected String name;
    protected String species;
    protected String breed;
    protected int age;
    protected double weight;
    protected String dietaryNotes;
    protected String behaviorNotes;

    /**
     * Constructs a Pet object with all attributes.
     */
    public Pet(int petId, String name, String species, String breed, int age, double weight,
               String dietaryNotes, String behaviorNotes) {
        this.petId = petId;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.dietaryNotes = dietaryNotes;
        this.behaviorNotes = behaviorNotes;
    }

    // Getters and setters
    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public String getDietaryNotes() { return dietaryNotes; }
    public void setDietaryNotes(String dietaryNotes) { this.dietaryNotes = dietaryNotes; }

    public String getBehaviorNotes() { return behaviorNotes; }
    public void setBehaviorNotes(String behaviorNotes) { this.behaviorNotes = behaviorNotes; }

    /**
     * Returns a formatted profile string specific to the pet type.
     * Must be implemented by subclasses (Dog, Cat).
     */
    public abstract String getProfile();

    @Override
    public String toString() {
        return getProfile();
    }
}