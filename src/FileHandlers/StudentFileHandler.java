package FileHandlers;

import HelperPrograms.CSVReader;
import HelperPrograms.MenuDisplay;
import Objects.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class StudentFileHandler {
    ArrayList<Student> allStudents = new ArrayList<>();

    public void setAllStudents(ArrayList<Student> allStudents) {
        this.allStudents = allStudents;
    }

    public ArrayList<Student> populateStudentData() {
        ArrayList<Student> listOfStudents = new ArrayList<>();
        CSVReader reader = new CSVReader();
        ArrayList<String> dataList = new ArrayList<>(reader.readCSVFile());

        for (String studentInfo : dataList) {
            String[] data = studentInfo.split(",");
            String studentID = data[0];
            String studentName = data[1];
            String birthDate = data[2];

            Student student = new Student(studentID, studentName, birthDate);

            if (!studentAlreadyExists(studentID, studentName, birthDate, listOfStudents)) {
                listOfStudents.add(student);
            }
        }
        return listOfStudents;
    }

    private static boolean studentAlreadyExists(String studentID, String studentName,
                                               String birthDate, List<Student> listOfStudents) {
        for (Student s : listOfStudents) {
            if (s.getStudentID().equals(studentID) ||
                    (s.getStudentName().equals(studentName)) && s.getBirthDate().equals(birthDate)) {
                return true;
            }
        }
        return false;
    }

    public void saveReportToCSV() {
        String fileName = gatherFileNameFromUserInput();
        try {
            File csvFile = new File(fileName);
            FileWriter writer = new FileWriter(csvFile);

            if (allStudents.isEmpty()) {
                System.out.println("No data retrieved! Please try again");
                writer.close();
            } else {
                for (Student student : allStudents) {
                    writer.append(student.convertToCSVRow());
                }
                writer.flush();
                writer.close();
                System.out.println("CSV file is successfully saved!");
            }
        } catch (IOException ioe) {
            System.out.println("Failed to save file! Please try again");
        }
    }

    public String gatherFileNameFromUserInput() {
        System.out.print("Please enter desired file name (including '.csv'): ");
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public static void promptUserToSave() throws InterruptedException {
        System.out.print("Do you wish to save this report? (Y/N): ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("y")) {
            StudentFileHandler s = new StudentFileHandler();
            s.saveReportToCSV();
        }
        else if (choice.equalsIgnoreCase("n")) {
            TimeUnit.SECONDS.sleep(1);
            MenuDisplay.userInterface();
        }
    }
}
