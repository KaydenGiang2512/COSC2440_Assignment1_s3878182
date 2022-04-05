package HelperPrograms;

import Objects.StudentEnrollment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVWriter {
    public File createNewCSVFile() {
        String fileName = gatherFileNameFromUserInput();
        File newCSVFile = new File(fileName);

        try {
            newCSVFile.createNewFile();
        } catch (IOException ioe) {
            System.out.println("An error has occurred!");
        }
        return newCSVFile;
    }

    public void writeDataToCSV() {
        ArrayList<StudentEnrollment> dataList = new ArrayList<>();

        try {
            FileWriter writer = new FileWriter(createNewCSVFile());
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

    public String gatherFileNameFromUserInput() {
        System.out.print("Please enter desired file name: ");
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }


}