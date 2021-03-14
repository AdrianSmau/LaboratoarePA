package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GaleShapleyAlgorithm extends Problem {
    public GaleShapleyAlgorithm(List<Student> students, List<School> schools, Map<Student, List<School>> studentPrefMap, Map<School, List<Student>> schoolPrefMap) {
        super(students, schools, studentPrefMap, schoolPrefMap);
    }

    /**
     * function that attempts to reassign the choices of a school
     * if the current student is higher in the list of priorities than another, already assigned student, the less wanted student is unassigned and the current one is assigned in his place
     * the unassigned one becomes the current one and so on, until the current one cant be assigned any further
     * then, we return the new map with the updated school choices and the student to be unassigned
     *
     * @param schoolChoices the map of the choices
     * @param studIndex     the index of the student that attempts to get into the school
     * @param schoolIndex   the index of the school
     * @return a container containing the new school choices map and the index of the student that gets unassigned
     */
    public updateSchoolChoicesReturn updateSchoolChoices(Map<School, List<Integer>> schoolChoices, int studIndex, int schoolIndex) {
        boolean solved = false; // is the assignation problem solved?
        while (!solved) {
            boolean changeMade = false; // check if a change was made
            for (Integer studentToBeChecked : schoolChoices.get(schools.get(schoolIndex))) { // we iterate through the current choices
                List<Student> currentSchoolPreferences = new ArrayList<>();
                for (Map.Entry<School, List<Student>> entry : schoolPrefMap.entrySet()) { // we iterate through the school pref map in order to see if the current student is in the current school's preference list
                    if (entry.getKey().equals(schools.get(schoolIndex)))
                        currentSchoolPreferences = entry.getValue();
                }
                if (currentSchoolPreferences.indexOf(students.get(studIndex)) < currentSchoolPreferences.indexOf(students.get(studentToBeChecked))) { // if the current school wants the checked student more than the student that is currently assigned
                    schoolChoices.get(schools.get(schoolIndex)).set(schoolChoices.get(schools.get(schoolIndex)).indexOf(studentToBeChecked), studIndex);
                    studIndex = studentToBeChecked; // the student that has been unassigned attempts to get into the current school again (maybe a student further down the list is less preferable than him)
                    changeMade = true;
                    break;
                }
            }
            if (!changeMade)
                solved = true;
        }
        return new updateSchoolChoicesReturn(schoolChoices, studIndex);
    }

    /**
     * Implements the Gale-Shapley algorithm for matching. The assignments are stored in the acceptedSchool map, between a school and the index of a school from the students List.
     * we iterate through every "free" student, and then through each school they applied to. If there is a free spot in that school, we assign the student and we move on
     * otherwise, we call the function updateSchoolChoices to see if the current students deserves a spot more than another student assigned to that school
     * the process goes on until all the students are assigned
     */
    public void GaleShapley() {
        int totalCapacities = 0;
        Map<School, List<Integer>> acceptedSchool = new HashMap<>(); //students accepted by each school
        //we populate each school's choice with -1
        for (School tempSchool : schools) {
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < tempSchool.getCapacity(); i++) {
                tempList.add(-1);
                totalCapacities++;
            }
            acceptedSchool.put(tempSchool, tempList);
        }
        if(students.size() > totalCapacities){
            System.out.println("The algorithm will not end properly!");
            System.exit(-1);
        }
        boolean[] freeStudent = new boolean[students.size()]; // signifies if this student is matched or not
        int unassignedStuds = students.size(); // number of students that have not been assigned
        int failSafe = 0;
        while (unassignedStuds > 0) {
            failSafe++;
            if (failSafe > totalCapacities*students.size()){
                System.out.println(" ----- WARNING : Some Student(/s) couldn't be assigned properly ----- \n");
                break;
            }
            int currentStudIndex = 0; // the index of the current student to be assigned
            for (int i = 0; i < students.size(); i++) {
                if (!freeStudent[i]) {
                    currentStudIndex = i;
                    break;
                }
            }
            for (int i = 0; i < studentPrefMap.get(students.get(currentStudIndex)).size() && !freeStudent[currentStudIndex]; i++) { // we iterate through the chosen student's school choices
                int currentSchoolIndex = schools.indexOf(studentPrefMap.get(students.get(currentStudIndex)).get(i)); // the index of the current school in the student's choices
                if (acceptedSchool.get(schools.get(currentSchoolIndex)).contains(-1)) { // if the current school has a free place
                    for (int j = 0; j < acceptedSchool.get(schools.get(currentSchoolIndex)).size(); j++) {
                        if (acceptedSchool.get(schools.get(currentSchoolIndex)).get(j) == -1) { // we place the current student in the free space
                            acceptedSchool.get(schools.get(currentSchoolIndex)).set(j, currentStudIndex);
                            unassignedStuds--; // the number of unassigned students decreases
                            freeStudent[currentStudIndex] = true; // the current student has been assigned
                            break;
                        }
                    }
                } else { // there isn't a free place, so we see if the current student is more preferred than any of the already placed ones
                    updateSchoolChoicesReturn resultContainer = updateSchoolChoices(acceptedSchool, currentStudIndex, currentSchoolIndex);
                    if (resultContainer.getChoiceMap().equals(acceptedSchool))
                        acceptedSchool = resultContainer.getChoiceMap();
                    freeStudent[currentStudIndex] = true;
                    freeStudent[resultContainer.getFreedStud()] = false;
                }
            }
        }
        //we print the result
        for (School school : acceptedSchool.keySet()) {
            System.out.println(school);
            for (Integer student : acceptedSchool.get(school)) {
                if (student > -1)
                    System.out.println("\t" + students.get(student));
                else
                    System.out.println("\t" + "FREE SPACE");
            }
        }
        System.out.println("\n ----- The Matchings are: ----- \n");
        Map<Student,School> solutionMap = new HashMap<>();
        for(School x : acceptedSchool.keySet()){
            for(Integer y : acceptedSchool.get(x)){
                if(y != -1)
                    solutionMap.put(students.get(y),x);
            }
        }
        System.out.println(new Solution(true,solutionMap));
    }
}
