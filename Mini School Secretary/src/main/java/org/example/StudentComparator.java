package org.example;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        // Compare by grade first
        int gradeComparison = Double.compare(s2.getGrade(), s1.getGrade());

        // If grades are equal, compare alphabetically by name
        if (gradeComparison == 0) {
            return s1.getName().compareTo(s2.getName());
        }

        return gradeComparison;
    }
}
