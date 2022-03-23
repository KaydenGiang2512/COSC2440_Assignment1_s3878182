package HelperPrograms;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class CSVReader {
    public static ArrayList<String> readCSVFile()  {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }
}