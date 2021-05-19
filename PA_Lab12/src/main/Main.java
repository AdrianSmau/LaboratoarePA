package main;

import main.myProgram.MyProgram;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n ----- Compulsory ----- \n");
        MyProgram programPerson = new MyProgram("main.testClasses.Person");
        programPerson.showInfo();
        programPerson.test();

        MyProgram programDog = new MyProgram("main.testClasses.Dog");
        programDog.showInfo();
        programDog.test();

        MyProgram programAnimal = new MyProgram("main.testClasses.Animal");
        programAnimal.showInfo();
        programAnimal.test();

        MyProgram programEating = new MyProgram("main.testClasses.Eating");
        programEating.showInfo();
        programEating.test();
    }
}
