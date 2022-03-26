package HelperPrograms;

import FileHandlers.CourseFileHandler;
import FileHandlers.StudentFileHandler;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MenuDisplay {
    public void userInterface() throws InterruptedException {
        System.out.println("\n+---------------------------------------------------------------------------------+");
        System.out.println("| Welcome, Administrator! This is the Java Enrollment Management System (J.E.M.S) |");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("| What would you like to do today?                                                |");
        System.out.println("+---------------------------------------------------------------------------------+");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("| 1/ View all students in the database                                            |");
        System.out.println("| 2/ View all courses in the database                                             |");
        System.out.println("| 0/ Terminate the running process & quit the program                             |");
        System.out.println("+---------------------------------------------------------------------------------+");
        System.out.print("> Your selection: ");
        Scanner inputScanner = new Scanner(System.in);
        String option = inputScanner.nextLine();

        boolean isRunning = true;

        while (isRunning) {
            switch (option) {
                case "0" -> {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Do you wish to exit the system? (Y/N): ");
                    String choice = sc.nextLine();
                    if (choice.equalsIgnoreCase("y")) {
                        quitProgram();
                    } else if (choice.equalsIgnoreCase("n")) {
                        System.out.println("Returning to main menu...");
                        userInterface();
                    } else {
                        System.out.println("Invalid command choice! Returning to main menu...");
                        TimeUnit.SECONDS.sleep(1);
                        userInterface();
                    }
                    isRunning = false;
                }
                case "1" -> {
                    StudentFileHandler s = new StudentFileHandler();
                    System.out.println(s.populateStudentData());
                    isRunning = false;
                    TimeUnit.SECONDS.sleep(1);
                    userInterface();
                }
                case "2" -> {
                    CourseFileHandler c = new CourseFileHandler();
                    System.out.println(c.populateCourseData());
                    isRunning = false;
                    TimeUnit.SECONDS.sleep(1);
                    userInterface();
                }
                default -> {
                    System.out.println("Invalid input! Please try again");
                    TimeUnit.SECONDS.sleep(1);
                    userInterface();
                }
            }
        }
    }

    private void quitProgram() {
        System.out.println("*****************************************");
        System.out.println("Exiting program... goodbye Administrator!");
        System.out.println("*****************************************");
        System.exit(0);
    }
}
