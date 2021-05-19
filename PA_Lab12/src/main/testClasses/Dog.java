package main.testClasses;

public class Dog extends Animal{
    @Override
    protected String getSound() {
        return "The Dog barks";
    }

    @Override
    public String eats() {
        return "Eats basically everything it comes in contact with";
    }
}
