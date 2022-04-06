package FileHandlers;

//Importing necessary packages
import HelperPrograms.CSVReader;
import HelperPrograms.MenuDisplay;
import Objects.Student;

//Importing necessary libraries
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class StudentFileHandler {

    //Creating a new ArrayList to hold data which will be saved to csv file
    private ArrayList<Student> allStudents = new ArrayList<>();

    //Setter and getter for the ArrayList
    public void setStudents(ArrayList<Student> allStudents) {
        this.allStudents = allStudents;
    }

    public ArrayList<Student> getStudents() {
        return allStudents;
    }

    //This method populates all student-related data from the default.csv file, and store them in an ArrayList
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

    //This method retrieves a single student object from the student ArrayList
    public Student populateOneStudentFromData(String studentID) throws InterruptedException {
        ArrayList<Student> allStudents = populateStudentData();
        ArrayList<Student> singleStudent = new ArrayList<>();

        studentID = gatherStudentFromUserInput();

        for (Student student : allStudents) {
            if (student.getStudentID().equalsIgnoreCase(studentID)) {
                singleStudent.add(student);
            }
        }
        if (singleStudent.isEmpty()) {
            System.out.println("Your search yielded no results! Please check all information and try again");
            MenuDisplay.userInterface();
        }
        System.out.println("\n*****************************************");
        System.out.println("Retrieving data of the queried student...");
        System.out.println("*****************************************\n");
        TimeUnit.SECONDS.sleep(1);
        return singleStudent.get(0);
    }

    //Checker to determine if the student is already added to the student ArrayList (to avoid duplication)
    private boolean studentAlreadyExists(String studentID, String studentName,
                                                String birthDate, List<Student> listOfStudents) {
        for (Student s : listOfStudents) {
            if (s.getStudentID().equals(studentID) ||
                    (s.getStudentName().equals(studentName)) && s.getBirthDate().equals(birthDate)) {
                return true;
            }
        }
        return false;
    }

    //Get user input of a studentID string
    public static String gatherStudentFromUserInput() {
        System.out.print("Please enter a student ID (ex. s000000): ");
        Scanner sc1 = new Scanner(System.in);

        return sc1.nextLine();
    }

    //This method creates a new csv file under the name that the user inputs, then writes the saved data into the file
    public void saveReportToCSV() throws InterruptedException {
        allStudents = getStudents();
        System.out.print("\nDo you wish to save this report? (Y/N): ");
        TimeUnit.SECONDS.sleep(1);
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("y")) {
            String fileName = gatherFileNameFromUserInput();
            try {
                File csvFile = new File(fileName);
                FileWriter writer = new FileWriter(csvFile);

                if (allStudents.isEmpty()) {
                    System.out.println("\n***********************************");
                    System.out.println("No data retrieved! Please try again");
                    System.out.println("***********************************");
                    writer.close();
                } else {
                    for (Student student : allStudents) {
                        writer.append(student.convertToCSVRow());
                    }
                    writer.flush();
                    writer.close();
                    System.out.println("\n*******************************");
                    System.out.println("CSV file is successfully saved!");
                    System.out.println("*******************************");
                }
            } catch (IOException ioe) {
                System.out.println("\n*************************************");
                System.out.println("Failed to save file! Please try again");
                System.out.println("*************************************");
            } catch (NullPointerException npe) {
                System.out.println("\n***********************************");
                System.out.println("No data retrieved! Please try again");
                System.out.println("***********************************");
            }
            TimeUnit.SECONDS.sleep(1);
            MenuDisplay.userInterface();
        }
        else if (choice.equalsIgnoreCase("n")) {
            TimeUnit.SECONDS.sleep(1);
            MenuDisplay.userInterface();
        }
    }

    //Get user input of a csv file name
    public String gatherFileNameFromUserInput() {
        System.out.print("Please enter desired file name (including '.csv'): ");
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }
}