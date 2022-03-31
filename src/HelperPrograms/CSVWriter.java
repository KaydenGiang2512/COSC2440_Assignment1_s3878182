package HelperPrograms;

import Objects.StudentEnrollment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVWriter {
    private final String fileName;

    public CSVWriter(String fileName) {
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

    public void writeDataToCSV() {
        createNewCSVFile();
        ArrayList<StudentEnrollment> dataList = new ArrayList<>();

        try {
            FileWriter writer = new FileWriter(fileName);
            StringBuilder builder = new StringBuilder();
            for (StudentEnrollment e : dataList) {
                builder.append(e.convertToCSVRow());
            }
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException ioe) {
            System.out.println("An error has occurred!");
        }
    }
}
