package org.example;

import java.util.ArrayList;

public class Course <Type extends Student> {
    private String name;
    private int maxCapacity;
    private int currentStudentCount;
    private ArrayList<Type> students;
    public Course(String name, int maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.currentStudentCount = 0;
        this.students = new ArrayList<Type>();
    }
    public void newStudent(Type student) {
        /* Adding a new student to the course */
        students.add(student);
        this.currentStudentCount++;
    }
    public String getName() {
        return this.name;
    }
    public int getCurrentStudentCount() {
        return this.currentStudentCount;
    }
    public int getMaxCapacity() {
        return this.maxCapacity;
    }
    public ArrayList<Type> getStudents() {
        return this.students;
    }
}
