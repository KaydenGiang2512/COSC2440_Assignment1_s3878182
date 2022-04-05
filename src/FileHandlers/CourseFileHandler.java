package FileHandlers;

import HelperPrograms.CSVReader;
import HelperPrograms.MenuDisplay;
import Objects.Course;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CourseFileHandler {
    ArrayList<Course> allCourses = new ArrayList<>();

    public void setAllCourses(ArrayList<Course> allCourses) {
        this.allCourses = allCourses;
    }

    public ArrayList<Course> populateCourseData() {
        CSVReader reader = new CSVReader();
        ArrayList<String> dataList = new ArrayList<>(reader.readCSVFile());
        ArrayList<Course> listOfCourses = new ArrayList<>();

        for (String courseInfo : dataList) {
            String[] data = courseInfo.split(",");
            String courseID = data[3];
            String courseName = data[4];
            int numberOfCredits = Integer.parseInt(data[5]);

            Course course = new Course(courseID, courseName, numberOfCredits);

            if (!courseAlreadyExists(courseID, courseName, numberOfCredits, listOfCourses)) {
                listOfCourses.add(course);
            }
        }
        return listOfCourses;
    }

    private static boolean courseAlreadyExists(String courseID, String courseName,
                                              int numberOfCredits, List<Course> listOfCourses) {
        for (Course c : listOfCourses) {
            if (c.getCourseID().equals(courseID) ||
                    (c.getCourseName().equals(courseName) && c.getNumberOfCredits() == numberOfCredits)) {
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

            if (allCourses.isEmpty()) {
                System.out.println("No data retrieved! Please try again");
                writer.close();
            } else {
                for (Course course : allCourses) {
                    writer.append(course.convertToCSVRow());
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
            CourseFileHandler c = new CourseFileHandler();
            c.saveReportToCSV();
        }
        else if (choice.equalsIgnoreCase("n")) {
            TimeUnit.SECONDS.sleep(1);
            MenuDisplay.userInterface();
        }
    }
}
