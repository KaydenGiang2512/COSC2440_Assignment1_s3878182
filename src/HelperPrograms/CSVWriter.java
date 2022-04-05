package HelperPrograms;

import Objects.Object;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVWriter {
    private String fileName = gatherFileNameFromUserInput();

    public File createNewCSVFile() {
        File newCSVFile = new File(fileName);

        try {
            newCSVFile.createNewFile();
        } catch (IOException ioe) {
            System.out.println("Failed to create csv file");
        }
        return newCSVFile;
    }

    public void writeDataToCSV(ArrayList<? extends Object> dataList) {
        createNewCSVFile();

        try {
            FileWriter writer = new FileWriter(fileName);
            StringBuilder builder = new StringBuilder();
            dataList.forEach(data -> builder.append(data.convertToCSVRow()));
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException ioe) {
            System.out.println("An error has occurred!");
        }
    }

    public String gatherFileNameFromUserInput() {
        System.out.print("Please enter desired file name (including '.csv'): ");
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public boolean saveReportToCSV() {
        System.out.println("Do you wish to save this report? (Y/N): ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("y")) {
            gatherFileNameFromUserInput();

            return true;
        }
        else if (choice.equalsIgnoreCase("n")) {
            return false;
        }
        return false;
    }

}