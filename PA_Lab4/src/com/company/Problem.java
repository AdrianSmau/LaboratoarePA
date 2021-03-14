package com.company;

import java.util.*;

public abstract class Problem {
    protected List<Student> students;
    protected List<School> schools;
    protected Map<Student, List<School>> studentPrefMap;
    protected Map<School, List<Student>> schoolPrefMap;

    public Problem(List<Student> students, Map<Student, List<School>> studentPrefMap, Map<School, List<Student>> schoolPrefMap) {
        this.students = new LinkedList<>();
        this.students = students;
        this.studentPrefMap = new HashMap<>();
        this.studentPrefMap = studentPrefMap;
        this.schoolPrefMap = new TreeMap<>();
        this.schoolPrefMap = schoolPrefMap;
    }

    public Problem(List<Student> students, List<School> schools, Map<Student, List<School>> studentPrefMap, Map<School, List<Student>> schoolPrefMap) {
        this.students = new LinkedList<>();
        this.students = students;
        this.studentPrefMap = new HashMap<>();
        this.studentPrefMap = studentPrefMap;
        this.schoolPrefMap = new TreeMap<>();
        this.schoolPrefMap = schoolPrefMap;
        this.schools = new ArrayList<>();
        this.schools = schools;
    }

    //student list methods

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Map<Student, List<School>> getStudentPrefMap() {
        return studentPrefMap;
    }

    //school pref map methods

    public void setStudentPrefMap(Map<Student, List<School>> studentPrefMap) {
        this.studentPrefMap = studentPrefMap;
    }

    //student pref map methods

    public Map<School, List<Student>> getSchoolPrefMap() {
        return schoolPrefMap;
    }

    public void setSchoolPrefMap(Map<School, List<Student>> schoolPrefMap) {
        this.schoolPrefMap = schoolPrefMap;
    }
}
