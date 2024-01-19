package org.example;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void commandExec(File[] files, Secretary secretary, String[] command, FileWriter fw) throws IOException {
        if (command[0].equals("adauga_student")) {
            /* Trying to add the student to the database, assuming he/she isn't already added */
            String studyCycle = command[1];
            String name = command[2];
            try {
                int flag = secretary.addStudent(name, studyCycle);
                if (flag == 1) {
                    throw new DuplicateStudentException();
                }
            } catch (DuplicateStudentException e) {
                fw.write("***\n");
                fw.write("Student duplicat: " + name + "\n");
            }
        } else if (command[0].equals("adauga_curs")) {
            /* Adding a new course to the database */
            String studyCycle = command[1];
            String name = command[2];
            String maxCapacity = command[3];
            secretary.addCourse(name, Integer.parseInt(maxCapacity), studyCycle);
        } else if (command[0].equals("citeste_mediile")) {
            /* Searching for the correct grade files and setting the grades for the mentioned students */
            for (File file : files) {
                if (file.getName().startsWith("note_")) {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] info = line.split(" - ");
                        secretary.setStudentGrades(info[0], Float.parseFloat(info[1]));
                    }
                    br.close();
                }
            }
        } else if (command[0].equals("posteaza_mediile")) {
            /* Getting the students array and writing the data needed in the output file */
            fw.write("***\n");
            ArrayList<Student> students = secretary.getStudents();
            secretary.sortStudents(students);
            for (Student student : students) {
                fw.write(student.getName() + " - " + student.getGrade() + "\n");
            }
        } else if (command[0].equals("contestatie")) {
            /* Getting the arguments from the command and then assigning the new grade to the student */
            String studentName = command[1];
            String newGrade = command[2];
            secretary.setStudentGrades(studentName, Float.parseFloat(newGrade));
        } else if (command[0].equals("adauga_preferinte")) {
            /* Adding the preferred courses for each student, according to the input file */
            String studentName = command[1];
            for (int i = 2; i < command.length; i++) {
                String courseName = command[i];
                Course<?> course = secretary.getCourse(courseName);
                secretary.addPreference(studentName, course);
            }
        } else if (command[0].equals("repartizeaza")) {
            /* Calling the repartition method from the Secretary class */
            secretary.repartition();
        } else if (command[0].equals("posteaza_curs")) {
            /* Writing a course's details in the output file, as asked */
            fw.write("***\n");
            String courseName = command[1];
            secretary.postCourse(courseName, fw);
        } else if (command[0].equals("posteaza_student")) {
            /* Writing a student's details in the output file, as asked */
            fw.write("***\n");
            String studentName = command[1];
            secretary.postStudent(studentName, fw);
        }
    }
    public static void main(String[] args) {
        try {
            String workingDirectory = "src/main/resources/" + args[0];
            String inputFile = workingDirectory + "/" + args[0] + ".in";
            String outputFile = workingDirectory + "/" + args[0] + ".out";

            File directory = new File(workingDirectory);
            File[] files = directory.listFiles();

            Secretary secretary = new Secretary();
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            FileWriter fw = new FileWriter(outputFile);

            String line;
            while ((line = br.readLine()) != null) {
                String[] command = line.split(" - ");
                commandExec(files, secretary, command, fw);
            }
            br.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
