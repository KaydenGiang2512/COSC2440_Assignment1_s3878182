package HelperPrograms;

import Objects.Student;
import Objects.Course;

import java.io.File;
import java.io.FileWriter;

public class CSVWriter {
    private final String fileName;

    public CSVWriter(String fileName) {
        this.fileName = String.join("", fileName) + ".csv";;
    }

    private File createNewCSVFile() {
        File newCSVFile = new File(fileName);

        return newCSVFile;
    }

//    public static void writeStudentDataToCSV() {
//        try {
//            FileWriter fileWriter = new FileWriter();
//
//        }
//    }
}
