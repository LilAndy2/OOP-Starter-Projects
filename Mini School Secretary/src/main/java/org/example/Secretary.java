package org.example;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Secretary {
    private ArrayList<Student> students;
    private ArrayList<Course<?>> courses;
    public Secretary() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }
    public ArrayList<Student> getStudents() {
        /* Returning the students array */
        return this.students;
    }
    public void sortStudents(ArrayList<Student> students) {
        /* Sorting the students array */
        students.sort(new StudentComparator());
    }
    public int addStudent(String name, String studyCycle) {
        /* Creating a new student, according to his/her study cycle */
        Student student;
        if (studyCycle.equals("licenta")) {
            student = new BachelorStudent(name, studyCycle);
        } else {
            student = new MasterStudent(name, studyCycle);
        }
        /* Verifying if student is already in the database */
        for (Student s : students) {
            if (s.getName().equals(name)) {
                return 1;
            }
        }
        /* If the student wasn't added previously, they will be added now */
        students.add(student);
        return 0;
    }
    public void setStudentGrades(String name, float grade) {
        /* Setting the grade for the corresponding student */
        for (Student student : students) {
            if (student.getName().equals(name)) {
                student.setGrade(grade);
            }
        }
    }
    public void addCourse(String name, int maxCapacity, String studyCycle) {
        /* Creating a new course and adding it to the courses array */
        Course<?> course;
        if (studyCycle.equals("licenta")) {
            course = new Course<BachelorStudent>(name, maxCapacity);
        } else {
            course = new Course<MasterStudent>(name, maxCapacity);
        }
        courses.add(course);
    }
    public Course<?> getCourse(String name) {
        /* Finding a course by its name */
        for (Course<?> course : courses) {
            if (course.getName().equals(name)) {
                return course;
            }
        }
        return null;
    }
    public void addPreference(String studentName, Course<?> course) {
        /* Adding a preferred course for a student */
        for (Student student : students) {
            if (student.getName().equals(studentName)) {
                /* Casting the course to the specific study cycle, according to the student's study cycle */
                if (student.getStudyCycle().equals("licenta")) {
                    BachelorStudent bachelorStudent = (BachelorStudent) student;
                    Course<BachelorStudent> bachelorCourse = (Course<BachelorStudent>) course;
                    bachelorStudent.addPreference(bachelorCourse);
                } else {
                    MasterStudent masterStudent = (MasterStudent) student;
                    Course<MasterStudent> masterCourse = (Course<MasterStudent>) course;
                    masterStudent.addPreference(masterCourse);
                }
            }
        }
    }
    public void repartition() {
        /* Sorting the students array and assigning them to the courses */
        this.students.sort(new StudentComparator());
        for (Student student : students) {
            ArrayList<Course<?>> preferences = student.getPreferences();
            for (Course<?> course : preferences) {
                /* Verifying if the course is full or not */
                if (course.getCurrentStudentCount() < course.getMaxCapacity()) {
                    student.setAssignedCourse(course);
                    if (student.getStudyCycle().equals("licenta")) {
                        BachelorStudent bachelorStudent = (BachelorStudent) student;
                        Course<BachelorStudent> bachelorCourse = (Course<BachelorStudent>) course;
                        bachelorCourse.newStudent(bachelorStudent);
                    } else {
                        MasterStudent masterStudent = (MasterStudent) student;
                        Course<MasterStudent> masterCourse = (Course<MasterStudent>) course;
                        masterCourse.newStudent(masterStudent);
                    }
                    break;
                } else {
                    /* If the course is full, the last student will be assigned to it in case his grade is equal to the
                    * last student's grade */
                    float lastGrade = course.getStudents().get(course.getMaxCapacity() - 1).getGrade();
                    if (student.getGrade() == lastGrade) {
                        student.setAssignedCourse(course);
                        if (student.getStudyCycle().equals("licenta")) {
                            BachelorStudent bachelorStudent = (BachelorStudent) student;
                            Course<BachelorStudent> bachelorCourse = (Course<BachelorStudent>) course;
                            bachelorCourse.newStudent(bachelorStudent);
                        } else {
                            MasterStudent masterStudent = (MasterStudent) student;
                            Course<MasterStudent> masterCourse = (Course<MasterStudent>) course;
                            masterCourse.newStudent(masterStudent);
                        }
                        break;
                    }
                }
            }
        }
    }
    public void postCourse(String courseName, FileWriter fw) throws IOException {
        /* Writing the course name and the students assigned to it in the output file */
        for (Course<?> course : courses) {
            if (course.getName().equals(courseName)) {
                fw.write(course.getName() + " (" + course.getMaxCapacity() + ")" + "\n");
                ArrayList<Student> students = (ArrayList<Student>) course.getStudents();
                students.sort(new StudentNameComparator());
                for (Student student : students) {
                    fw.write(student.getName() + " - " + student.getGrade() + "\n");
                }
            }
        }
    }
    public void postStudent(String studentName, FileWriter fw) throws IOException {
        /* Writing the student name and the course they were assigned to in the output file */
        for (Student student : students) {
            if (student.getName().equals(studentName)) {
                fw.write("Student " + student.getStudyCycle().substring(0,1).toUpperCase() + student.getStudyCycle().substring(1) + ": "+ student.getName() + " - " + student.getGrade() + " - " + student.getAssignedCourse().getName() + "\n");
            }
        }
    }
}
