package com.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchingAlgorithm extends Problem {
    public MatchingAlgorithm(List<Student> students, Map<Student, List<School>> studentPrefMap, Map<School, List<Student>> schoolPrefMap) {
        super(students, studentPrefMap, schoolPrefMap);
    }

    /**
     * Having the map of students' preferences and the list of students ordered by their grade, we apply the "Best score, first served" principle
     * The Schools rank their students of choice by grade, so by taking each student by their grade and assigning them to the first school that they want that they are eligible for(the school wants them and the school isn't already full),
     * we assure that the matches are stable. If a student can't be assigned, this will be signaled in the Solution class
     *
     * @return new Solution(TRUE, mapOfMatchings) if a matching can be done, new Solution(FALSE) otherwise
     */
    public Solution solveMatching() {
        Map<Student, School> solutionMap = new HashMap<>();
        for (Student currentStudent : students) { // we iterate through the best students first
            boolean matchFound = false;
            for (School currentSchool : studentPrefMap.get(currentStudent)) { // we iterate through their preferences, from the best to the worst
                for (Map.Entry<School, List<Student>> entry : schoolPrefMap.entrySet()) { // we iterate through the school pref map in order to see if the current student is in the current school's preference list
                    if (entry.getKey().equals(currentSchool) && entry.getValue().contains(currentStudent) && currentSchool.getCapacity() > 0) { // if the student is in the school's preference list and the school's capacity is above 0
                        matchFound = true; // match found!
                        currentSchool.setCapacity(currentSchool.getCapacity() - 1);
                        solutionMap.put(currentStudent, currentSchool);
                        break;
                    }
                }
                if (matchFound)
                    break;
            }
            if (!matchFound) // no match has been found for the current student, so a matching isn't possible
                return new Solution(false);
        }
        return new Solution(true, solutionMap);
    }
}
