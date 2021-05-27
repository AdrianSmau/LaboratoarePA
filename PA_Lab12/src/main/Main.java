package main;

import main.myProgram.MyProgram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static List<String> parseFolder(String path) {
        List<String> resultedClassNames = new ArrayList<>();
        List<Path> paths = new ArrayList<>();

        try {
            paths = Files.find(Paths.get(path),
                    Integer.MAX_VALUE,
                    (filePath, fileAttr) -> fileAttr.isRegularFile())
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            System.err.println("[ERROR] IOException caught!");
        }

        for (Path x : paths) {
            String pathString = x.toString();
            pathString = pathString.replace("\\", "/");
            List<String> elems = Arrays.asList(pathString.split("/"));
            Collections.reverse(elems);

            String mainPackage = Main.class.getPackageName();

            StringBuilder className = new StringBuilder();

            if (elems.contains(mainPackage) && Arrays.asList(elems.get(0).split("[.]")).size() == 2 && Arrays.asList(elems.get(0).split("[.]")).get(1).equals("java")) {
                for (String y : elems) {
                    if (y.equals(mainPackage)) {
                        className.append(y);
                        break;
                    }
                    if (y.equals(elems.get(0))) {
                        className.append(y.split("[.]")[0]).append(".");
                        continue;
                    }
                    className.append(y).append(".");
                }
                System.out.println("ClassPath found!");
                String[] components = className.toString().split("[.]");
                StringBuilder res = new StringBuilder();
                for (int i = components.length - 1; i > 0; i--) {
                    res.append(components[i]).append(".");
                }
                res.append(components[0]);
                String finalClassName = res.toString();

                System.out.println("ClassPath is: " + finalClassName);
                resultedClassNames.add(finalClassName);
            } else
                System.out.println("File not ClassPath!");
        }
        return resultedClassNames;
    }

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

        System.out.println("\n ----- Optional ----- \n");

        List<String> foundClasses = parseFolder("C:\\Users\\adria\\Desktop\\PA\\PA_Lab12");
        for (String x : foundClasses) {
            MyProgram programGenerated = new MyProgram(x);
            programGenerated.showInfo();
            programGenerated.test();
        }
    }
}
