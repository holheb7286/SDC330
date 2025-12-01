/*
* Cat class extending Pet. Demonstrates inheritance and polymorphism.
*/

public class Cat extends Pet {

    private boolean declawed;

    public Cat(int petId, String name, String breed, int age, double weight,
               String dietaryNotes, String behaviorNotes, boolean declawed) {
        super(petId, name, "Cat", breed, age, weight, dietaryNotes, behaviorNotes);
        this.declawed = declawed;
    }

    public boolean isDeclawed() { return declawed; }
    public void setDeclawed(boolean declawed) { this.declawed = declawed; }

    @Override
    public String getProfile() {
        return "Cat Profile:\n" +
               "ID: " + petId + "\nName: " + name + "\nBreed: " + breed +
               "\nAge: " + age + " years\nWeight: " + weight + " lbs\n" +
               "Declawed: " + (declawed ? "Yes" : "No") + "\nDietary Notes: " + dietaryNotes +
               "\nBehavior Notes: " + behaviorNotes + "\n";
    }
}