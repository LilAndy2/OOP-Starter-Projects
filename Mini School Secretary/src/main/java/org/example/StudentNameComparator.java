package org.example;

import java.util.Comparator;

public class StudentNameComparator implements Comparator<Student> {
    public int compare(Student student1, Student student2) {
        // Compare names alphabetically
        return student1.getName().compareTo(student2.getName());
    }
}
