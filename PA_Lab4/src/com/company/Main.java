package com.company;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n\n ----- Compulsory ----- \n");

        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);
        List<Integer> schoolCapacities = Arrays.asList(1, 2, 2);
        var schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i, schoolCapacities.get(i)))
                .toArray(School[]::new);
        //List of Students
        List<Student> studentList = new LinkedList<>(Arrays.asList(students));
        //Set of Schools
        Set<School> schoolSet = new TreeSet<>(Stream.of(schools).collect(Collectors.toSet()));
        Collections.sort(studentList, Comparator.comparing(Student::getName));
        //Students' preferences
        Map<Student, List<School>> studentPrefMap = new HashMap<>();
        studentPrefMap.put(students[0], Arrays.asList(schools[0], schools[1], schools[2]));
        studentPrefMap.put(students[1], Arrays.asList(schools[0], schools[1], schools[2]));
        studentPrefMap.put(students[2], Arrays.asList(schools[0], schools[1]));
        studentPrefMap.put(students[3], Arrays.asList(schools[0], schools[2]));
        System.out.println("\n ----- Printing the Students' preferences!... ----- \n");
        studentPrefMap.forEach((key, value) -> {
                    System.out.print("Student " + key.getName() + " has the following preferences: " + value.get(0).getName());
                    value.forEach((subValue) -> {
                                if (!subValue.equals(value.get(0)))
                                    System.out.print(" , " + subValue.getName());
                            }
                    );
                    System.out.print("\n");
                }
        );
        //Schools' preferences
        Map<School, List<Student>> schoolPrefMap = new TreeMap<>();
        schoolPrefMap.put(schools[0], Arrays.asList(students[3], students[0], students[1], students[2]));
        schoolPrefMap.put(schools[1], Arrays.asList(students[0], students[2], students[1]));
        schoolPrefMap.put(schools[2], Arrays.asList(students[0], students[1], students[3]));
        System.out.println("\n ----- Printing the Schools' preferences!... ----- \n");
        schoolPrefMap.forEach((key, value) -> {
                    System.out.print("School " + key.getName() + " has the following preferences: " + value.get(0).getName());
                    value.forEach((subValue) -> {
                                if (!subValue.equals(value.get(0)))
                                    System.out.print(" , " + subValue.getName());
                            }
                    );
                    System.out.print("\n");
                }
        );

        System.out.println("\n\n ----- Optional ----- \n");

        System.out.println("\n ----- Schools that have the student S0 as their top preference!... ----- \n");
        schoolSet.stream()
                .filter(std -> schoolPrefMap.get(std).get(0).equals(students[0]))
                .forEach(System.out::println);
        System.out.println("\n ----- Students that accept the Schools H1 and H0!... ----- \n");
        List<School> target = Arrays.asList(schools[1], schools[0]);
        List<Student> result = studentList.stream()
                .filter(std -> studentPrefMap.get(std).containsAll(target))
                .collect(Collectors.toList());
        for (int i = 0; i < result.size(); i++)
            System.out.println(result.get(i));
        Faker faker = new Faker();
        System.out.println("\n ----- We generate random names (and Student grades) using the JavaFaker libraries!... ----- \n");
        for (int i = 0; i < studentList.size(); i++) {
            studentList.get(i).setName(faker.name().fullName());
            studentList.get(i).setGrade(faker.number().randomDouble(2, 5, 10));
            System.out.println("Student S" + i + " : " + studentList.get(i).getName() + " has grade: " + studentList.get(i).getGrade());
        }
        int index = 0;
        for (School iterator : schoolSet) {
            iterator.setName(faker.address().streetName() + " High School");
            System.out.println("School H" + index + " : " + iterator.getName());
            index++;
        }
        for (List<Student> eachList : schoolPrefMap.values())
            Collections.sort(eachList, Comparator.comparing(Student::getGrade).reversed());
        System.out.println("\n ----- Printing the Schools' preferences after sorting by grade!... ----- \n");
        schoolPrefMap.forEach((key, value) -> {
                    System.out.print("School " + key.getName() + " has the following preferences: " + value.get(0).getName() + ", with grade: " + value.get(0).getGrade());
                    value.forEach((subValue) -> {
                                if (!subValue.equals(value.get(0)))
                                    System.out.print(" , " + subValue.getName() + ", with grade: " + subValue.getGrade());
                            }
                    );
                    System.out.print("\n");
                }
        );
        Collections.sort(studentList, Comparator.comparing(Student::getGrade).reversed());
        List<School> schoolList = schoolSet.stream()
                .collect(Collectors.toList());
        Integer[] tempSchoolsCapacities = new Integer[schools.length];
        for (int i = 0; i < schools.length; i++)
            tempSchoolsCapacities[i] = schools[i].getCapacity();
        System.out.println("\n ----- Printing the Matchings... ----- \n");
        MatchingAlgorithm matchingProblem = new MatchingAlgorithm(studentList, studentPrefMap, schoolPrefMap);
        System.out.println(matchingProblem.solveMatching());
        for (int i = 0; i < schools.length; i++) {
            schools[i].setCapacity(tempSchoolsCapacities[i]);
        }

        System.out.println("\n\n ----- Bonus ----- \n");

        //Let's presume the schools rank their preferences based on the value of the student's name in ascii mod 10
        for (List<Student> eachList : schoolPrefMap.values())
            Collections.sort(eachList, (Object s1, Object s2) -> {
                        if (s1 instanceof Student && s2 instanceof Student) {
                            int compare = (int) ((Student) s1).getName().charAt(0) % 10 - (int) ((Student) s2).getName().charAt(0) % 10;
                            if (compare > 0)
                                return -1;
                            else if (compare < 0)
                                return 1;
                        }
                        return 0;
                    }
            );
        System.out.println("\n ----- Printing the Schools' preferences after sorting by ASCII value of first character of name, mod 10!... ----- \n");
        schoolPrefMap.forEach((key, value) -> {
                    System.out.print("School " + key.getName() + " has the following preferences: " + value.get(0).getName() + ", with grade: " + value.get(0).getGrade());
                    value.forEach((subValue) -> {
                                if (!subValue.equals(value.get(0)))
                                    System.out.print(" , " + subValue.getName() + ", with grade: " + subValue.getGrade());
                            }
                    );
                    System.out.print("\n");
                }
        );
        System.out.println("\n ----- We apply the Gale-Shapley algorithm!... ----- \n");
        GaleShapleyAlgorithm galeShapleyProblem = new GaleShapleyAlgorithm(studentList, schoolList, studentPrefMap, schoolPrefMap);
        galeShapleyProblem.GaleShapley();
        System.out.println("\nIf we consider the case in which the preferences are not necessarily strict, such that consecutive preferences in the school's preference list form a tie, we could modify our approach to the Gale-Shapley algorithm easily by simply checking if the elements are equal in the schoolPrefMap!");
        System.out.println("In the class GaleShapleyAlgorithm, within the method updateSchoolChoices, before checking if the index of the current student is smaller than the index of the current element, we check if they are equal in the map. If they are, instead of selecting one of them for that specific place, we place both of them in the same place.");
        System.out.println("Then, we mark both of them as matched. This could potentially increase our number of stable matchings, as more than one possibilities are plausible.");
    }
}
