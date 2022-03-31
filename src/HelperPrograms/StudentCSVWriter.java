package HelperPrograms;

import Objects.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentCSVWriter {
    private final String fileName;

    public StudentCSVWriter(String fileName) {
        this.fileName = fileName;
    }

    public File createNewCSVFile() {
        File newCSVFile = new File(fileName);

        try {
            newCSVFile.createNewFile();
        } catch (IOException ioe) {
            System.out.println("An error has occurred!");
        }
        return newCSVFile;
    }

    public void writeStudentDataToCSV(ArrayList<Student> listOfStudents) {
        createNewCSVFile();
        try {
            FileWriter writer = new FileWriter(fileName);
            StringBuilder builder = new StringBuilder();
            for (Student s : listOfStudents) {
                builder.append(s.convertToCSVRow());
            }
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException ioe) {
            System.out.println("An error has occurred!");
        }
    }
}
