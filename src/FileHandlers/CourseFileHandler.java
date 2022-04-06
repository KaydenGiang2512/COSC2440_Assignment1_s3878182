package FileHandlers;

//Importing necessary packages
import HelperPrograms.CSVReader;
import HelperPrograms.MenuDisplay;
import Objects.Course;

//Importing necessary libraries
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CourseFileHandler {

    //Creating a new ArrayList to hold data which will be saved to csv file
    private ArrayList<Course> allCourses = new ArrayList<>();

    //Setter and getter for the ArrayList
    public void setCourses(ArrayList<Course> allCourses) {
        this.allCourses = allCourses;
    }

    public ArrayList<Course> getCourses() {
        return allCourses;
    }

    //This method populates all course-related data from the default.csv file, and store them in an ArrayList
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

    //This method retrieves a single course object from the course ArrayList
    public Course populateOneCourseFromData(String courseID) throws InterruptedException {
        ArrayList<Course> allCourses = populateCourseData();
        ArrayList<Course> singleCourse = new ArrayList<>();

        courseID = gatherCourseFromUserInput();

        for (Course course : allCourses) {
            if (course.getCourseID().equalsIgnoreCase(courseID)) {
                singleCourse.add(course);
            }
        }
        if (singleCourse.isEmpty()) {
            System.out.println("Your search yielded no results! Please check all information and try again");
            MenuDisplay.userInterface();
        }
        System.out.println("\n*****************************************");
        System.out.println("Retrieving data of the queried course...");
        System.out.println("*****************************************\n");
        TimeUnit.SECONDS.sleep(1);
        return singleCourse.get(0);
    }

    //Checker to determine if the course is already added to the course ArrayList (to avoid duplication)
    private boolean courseAlreadyExists(String courseID, String courseName,
                                               int numberOfCredits, List<Course> listOfCourses) {
        for (Course c : listOfCourses) {
            if (c.getCourseID().equals(courseID) ||
                    (c.getCourseName().equals(courseName) && c.getNumberOfCredits() == numberOfCredits)) {
                return true;
            }
        }
        return false;
    }

    //Get user input of a courseID string
    public static String gatherCourseFromUserInput() {
        System.out.print("Please enter a course ID (ex. COSC0000): ");
        Scanner sc2 = new Scanner(System.in);

        return sc2.nextLine();
    }

    //This method creates a new csv file under the name that the user inputs, then writes the saved data into the file
    public void saveReportToCSV() throws InterruptedException {
        allCourses = getCourses();
        System.out.print("\nDo you wish to save this report? (Y/N): ");
        TimeUnit.SECONDS.sleep(1);
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("y")) {
            String fileName = gatherFileNameFromUserInput();
            try {
                File csvFile = new File(fileName);
                FileWriter writer = new FileWriter(csvFile);

                if (allCourses.isEmpty()) {
                    System.out.println("\n***********************************");
                    System.out.println("No data retrieved! Please try again");
                    System.out.println("***********************************");
                    writer.close();
                } else {
                    for (Course course : allCourses) {
                        writer.append(course.convertToCSVRow());
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