/*
* Dog class extending Pet. Demonstrates inheritance and polymorphism.
*/

public class Dog extends Pet {

    private String trainingLevel;

    public Dog(int petId, String name, String breed, int age, double weight,
               String dietaryNotes, String behaviorNotes, String trainingLevel) {
        super(petId, name, "Dog", breed, age, weight, dietaryNotes, behaviorNotes);
        this.trainingLevel = trainingLevel;
    }

    public String getTrainingLevel() { return trainingLevel; }
    public void setTrainingLevel(String trainingLevel) { this.trainingLevel = trainingLevel; }

    @Override
    public String getProfile() {
        return "Dog Profile:\n" +
               "ID: " + petId + "\nName: " + name + "\nBreed: " + breed +
               "\nAge: " + age + " years\nWeight: " + weight + " lbs\n" +
               "Training Level: " + trainingLevel + "\nDietary Notes: " + dietaryNotes +
               "\nBehavior Notes: " + behaviorNotes + "\n";
    }
}