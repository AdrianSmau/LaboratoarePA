package main.myProgram;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyProgram {
    private final Class<?> currentClass;

    public MyProgram(String className) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            System.err.println("[ERROR] Class not found");
            clazz = null;
        } finally {
            this.currentClass = clazz;
        }
    }

    @Test
    private static void personTest(Class<?> clazz) {
        String packageName = clazz.getPackage().getName();
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getMethods();
        Constructor<?>[] constructors = clazz.getConstructors();
        int accessModifier = clazz.getModifiers();

        try {
            assertEquals("main.testClasses", packageName);
            assertEquals(1, constructors.length);
            assertEquals(9, methods.length);
            assertEquals(2, fields.length);
            assertTrue(Modifier.isPublic(accessModifier));
        } catch (AssertionFailedError ex) {
            System.err.println("[TEST FAILED] Person Class Test Failed!");
            return;
        }
        System.out.println("[TEST SUCCESSFUL] Person Class Test Passed!");
    }

    @Test
    private static void animalTest(Class<?> clazz) {
        String packageName = clazz.getPackage().getName();
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getMethods();
        Constructor<?>[] constructors = clazz.getConstructors();
        int accessModifier = clazz.getModifiers();

        try {
            assertEquals("main.testClasses", packageName);
            assertEquals(1, constructors.length);
            assertEquals(10, methods.length);
            assertEquals(1, fields.length);
            assertEquals("Eating", clazz.getInterfaces()[0].getSimpleName());
            assertTrue(Modifier.isPublic(accessModifier));
            assertTrue(Modifier.isAbstract(accessModifier));
        } catch (AssertionFailedError ex) {
            System.err.println("[TEST FAILED] Animal Class Test Failed!");
            return;
        }
        System.out.println("[TEST SUCCESSFUL] Animal Class Test Passed!");
    }

    @Test
    private static void eatingTest(Class<?> clazz) {
        String packageName = clazz.getPackage().getName();
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getMethods();
        Constructor<?>[] constructors = clazz.getConstructors();
        int accessModifier = clazz.getModifiers();

        try {
            assertEquals("main.testClasses", packageName);
            assertEquals(0, constructors.length);
            assertEquals(1, methods.length);
            assertEquals(0, fields.length);
            assertTrue(Modifier.isPublic(accessModifier));
            assertTrue(Modifier.isInterface(accessModifier));
        } catch (AssertionFailedError ex) {
            System.err.println("[TEST FAILED] Person Class Test Failed!");
            return;
        }
        System.out.println("[TEST SUCCESSFUL] Person Class Test Passed!");
    }

    @Test
    private static void dogTest(Class<?> clazz) {
        String packageName = clazz.getPackage().getName();
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getMethods();
        Constructor<?>[] constructors = clazz.getConstructors();
        int accessModifier = clazz.getModifiers();

        try {
            assertEquals("main.testClasses", packageName);
            assertEquals(1, constructors.length);
            assertEquals(10, methods.length);
            assertEquals(0, fields.length);
            assertEquals("Animal", clazz.getSuperclass().getSimpleName());
            assertTrue(Modifier.isPublic(accessModifier));
        } catch (AssertionFailedError ex) {
            System.err.println("[TEST FAILED] Dog Class Test Failed!");
            return;
        }
        System.out.println("[TEST SUCCESSFUL] Dog Class Test Passed!");
    }

    public void test() {
        if (this.currentClass == null) {
            System.out.println("Something went wrong when trying to get the class! No tests to be ran!");
            return;
        }
        switch (this.currentClass.getSimpleName()) {
            case "Animal":
                animalTest(this.currentClass);
                break;
            case "Person":
                personTest(this.currentClass);
                break;
            case "Eating":
                eatingTest(this.currentClass);
                break;
            case "Dog":
                dogTest(this.currentClass);
                break;
            default:
                System.out.println("No tests implemented yet for this type of Class!");
                break;
        }
    }

    public void showInfo() {
        if (this.currentClass == null) {
            System.out.println("Something went wrong when trying to get the class! No info to be shown!");
            return;
        }
        System.out.println("\n ----- Showing Class Information for class: " + this.currentClass.getName() + " ----- \n");
        System.out.println("Class name: " + this.currentClass.getSimpleName());
        System.out.println("Package name: " + this.currentClass.getPackage().getName());
        System.out.println("---");
        System.out.println("Fields: " + Arrays.toString(this.currentClass.getDeclaredFields()));
        System.out.println("Constructors: " + Arrays.toString(this.currentClass.getConstructors()));
        System.out.println("Methods: " + Arrays.toString(this.currentClass.getMethods()));
        System.out.println("---");
        int modifiers = this.currentClass.getModifiers();
        System.out.println("Is our class public?: " + Modifier.isPublic(modifiers));
        System.out.println("Is our class private?: " + Modifier.isPrivate(modifiers));
        System.out.println("Is our class protected?: " + Modifier.isProtected(modifiers));
        System.out.println("Is our class an interface?: " + Modifier.isInterface(modifiers));
        System.out.println("Is our class abstract?: " + Modifier.isAbstract(modifiers));
        System.out.println("---");
        Class<?> currentClassSuperClass = this.currentClass.getSuperclass();
        try {
            System.out.println("Our class has the Superclass: " + currentClassSuperClass.getName());
        } catch (NullPointerException ex) {
            System.out.println("Our class has no Superclass");
        }
        System.out.println("Implemented Interfaces: " + Arrays.toString(this.currentClass.getInterfaces()));
    }
}
