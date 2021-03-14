package com.company;

import java.util.List;
import java.util.Map;

/**
 * container type class for returning the result in case a reassignment of student is necessary
 */
public class updateSchoolChoicesReturn { // a container class that returns the new school choices map and the index of the student that has been unassigned again
    final private Map<School, List<Integer>> choiceMap;
    final private int freedStud;

    public updateSchoolChoicesReturn(Map<School, List<Integer>> choiceMap, int freedStud) {
        this.choiceMap = choiceMap;
        this.freedStud = freedStud;
    }

    public Map<School, List<Integer>> getChoiceMap() {
        return choiceMap;
    }

    public int getFreedStud() {
        return freedStud;
    }
}
