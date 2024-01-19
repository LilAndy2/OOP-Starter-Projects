package org.example;

import java.util.ArrayList;

public class Student {
    private final String name;
    private float grade;
    private ArrayList<Course<?>> preferences;
    private Course<?> assignedCourse;
    private final String studyCycle;
    public Student(String name, String studyCycle) {
        this.name = name;
        this.studyCycle = studyCycle;
        this.preferences = new ArrayList<Course<?>>();
    }
    public String getName() {
        return this.name;
    }
    public float getGrade() {
        return this.grade;
    }
    public void setGrade(float grade) {
        this.grade = grade;
    }
    public void addPreference(Course<?> course) {
        this.preferences.add(course);
    }
    public ArrayList<Course<?>> getPreferences() {
        return this.preferences;
    }
    public void setAssignedCourse(Course<?> course) {
        this.assignedCourse = course;
    }
    public String getStudyCycle() {
        return this.studyCycle;
    }
    public Course<?> getAssignedCourse() {
        return this.assignedCourse;
    }
}
