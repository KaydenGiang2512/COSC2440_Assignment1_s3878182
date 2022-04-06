package HelperPrograms;

//Importing necessary libraries
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class CSVReader {

    //This method reads the entire default.csv file line by line, then return and ArrayList of strings
    public ArrayList<String> readCSVFile() {
        ArrayList<String> rows = new ArrayList<>();
        String pathToCSV = "default.csv";

        try {
            FileReader fReader = new FileReader(pathToCSV);
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            while (line != null) {
                rows.add(line);
                line = bReader.readLine();
            }
            System.out.println();
            fReader.close();
            bReader.close();
        } catch (IOException ioe) {
            System.out.println("An error has occurred!");
        }
        return rows;
    }
}
