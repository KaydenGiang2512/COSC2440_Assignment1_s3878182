package HelperPrograms;

import AcademicManagement.AcademicManager;
import FileHandlers.CourseFileHandler;
import FileHandlers.StudentFileHandler;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MenuDisplay {
    public static void userInterface() throws InterruptedException {
        System.out.println("\n+---------------------------------------------------------------------------------+");
        System.out.println("| INITIALIZING ALL J.E.M.S PROCESSES...                                           |");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("| Welcome, Administrator! This is the Java Enrollment Management System (J.E.M.S) |");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("| What tasks would you like to perform today (0-10)?                              |");
        System.out.println("|                                                                                 |");
        System.out.println("|*********************************************************************************|");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("| 1/ View all students in the database                                            |");
        System.out.println("| 2/ View all courses in the database                                             |");
        System.out.println("| 3/ View all enrollments in the database                                         |");
        System.out.println("| 4/ Add a new enrollment to the database                                         |");
        System.out.println("| 5/ Remove an existing enrollment from the database                              |");
        System.out.println("| 6/ View a particular enrollment in the database                                 |");
        System.out.println("| 7/ View all available courses for a particular student in a semester            |");
        System.out.println("| 8/ View all students in a particular course in a semester                       |");
        System.out.println("| 9/ View all available courses in a semester                                     |");
        System.out.println("|                                                                                 |");
        System.out.println("| 0/ Terminate all processes & quit the program                                   |");
        System.out.println("+---------------------------------------------------------------------------------+");
        System.out.print("> Your selection: ");
        Scanner inputScanner = new Scanner(System.in);
        String option = inputScanner.nextLine();

        boolean isRunning = true;

        while (isRunning) {
            switch (option) {
                case "0" -> {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("\nDo you wish to exit the system? (Y/N): ");
                    String choice = sc.nextLine();

                    if (choice.equalsIgnoreCase("y")) {
                        quitProgram();
                    } else if (choice.equalsIgnoreCase("n")) {
                        System.out.println("\n*************************");
                        System.out.println("Returning to main menu...");
                        System.out.println("*************************");
                        TimeUnit.SECONDS.sleep(1);
                        userInterface();
                    } else {
                        System.out.println("\n*************************************************");
                        System.out.println("Invalid command choice! Returning to main menu...");
                        System.out.println("*************************************************");
                        TimeUnit.SECONDS.sleep(1);
                        userInterface();
                    }
                    isRunning = false;
                }
                case "1" -> {
                    System.out.println("\n**********************************");
                    System.out.println("Retrieving all data of students...");
                    System.out.println("**********************************");
                    TimeUnit.SECONDS.sleep(1);
                    AcademicManager admin = new AcademicManager();
                    System.out.println(admin.displayAllStudents());
                    isRunning = false;
                    TimeUnit.SECONDS.sleep(1);
                    userInterface();
                }
                case "2" -> {
                    System.out.println("\n*********************************");
                    System.out.println("Retrieving all data of courses...");
                    System.out.println("*********************************");
                    TimeUnit.SECONDS.sleep(1);
                    AcademicManager admin = new AcademicManager();
                    System.out.println(admin.displayAllCourses());
                    isRunning = false;
                    TimeUnit.SECONDS.sleep(1);
                    userInterface();
                }
                case "3" -> {
                    System.out.println("\n*************************************");
                    System.out.println("Retrieving all data of enrollments...");
                    System.out.println("*************************************");
                    TimeUnit.SECONDS.sleep(1);
                    AcademicManager admin = new AcademicManager();
                    System.out.println(admin.displayAllEnrollments());
                    isRunning = false;
                    TimeUnit.SECONDS.sleep(1);
                    userInterface();
                }
                case "4" -> {
                    AcademicManager admin = new AcademicManager();
                    System.out.println(admin.addNewEnrollment());
                    isRunning = false;
                    TimeUnit.SECONDS.sleep(1);
                    userInterface();
                }
                case "5" -> {
                    AcademicManager admin = new AcademicManager();
                    System.out.println(admin.deleteExistingEnrollment());
                    isRunning = false;
                    TimeUnit.SECONDS.sleep(1);
                    userInterface();
                }
                case "6" -> {
                    AcademicManager admin = new AcademicManager();
                    System.out.println(admin.displayOneEnrollment());
                    isRunning = false;
                    TimeUnit.SECONDS.sleep(1);
                    userInterface();
                }
                case "7" -> {
                    AcademicManager admin = new AcademicManager();
                    System.out.println(admin.displayAllCoursesForStudentInSemester());
                    CourseFileHandler.promptUserToSave();
                    isRunning = false;
                    TimeUnit.SECONDS.sleep(1);
                    userInterface();
                }
                case "8" -> {
                    AcademicManager admin = new AcademicManager();
                    System.out.println(admin.displayAllStudentsInCourseInSemester());
                    StudentFileHandler.promptUserToSave();
                    isRunning = false;
                    TimeUnit.SECONDS.sleep(1);
                    userInterface();
                }
                case "9" -> {
                    AcademicManager admin = new AcademicManager();
                    System.out.println(admin.displayAllCoursesInSemester());
                    CourseFileHandler.promptUserToSave();
                    isRunning = false;
                    TimeUnit.SECONDS.sleep(1);
                    userInterface();
                }
                default -> {
                    System.out.println("\n***********************************************");
                    System.out.println("Invalid input option! Returning to main menu...");
                    System.out.println("***********************************************");
                    TimeUnit.SECONDS.sleep(1);
                    userInterface();
                }
            }
        }
    }

    private static void quitProgram () throws InterruptedException {
        System.out.println("\n*****************************************");
        System.out.println("Exiting program... goodbye Administrator!");
        System.out.println("*****************************************");
        TimeUnit.SECONDS.sleep(1);
        System.exit(0);
    }

    public static void selectCSVFileAsDatabase() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("\n+----------------------------------------------------------------+");
        System.out.println("| DATABASE FILE NOT YET SELECTED! PLEASE CHOOSE A METHOD BELOW   |");
        System.out.println("|                                                                |");
        System.out.println("|****************************************************************|");
        System.out.println("| 1/ Automatically select the 'default.csv' file as the database |");
        System.out.println("| 2/ Manually import a csv file to the system as the database    |");
        System.out.println("+----------------------------------------------------------------+");
        System.out.print("> Your selection: ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();

        if (choice.equals("1")) {
            System.out.println("\n***********************************************************************");
            System.out.println("The system will automatically use 'default.csv' as its database file...");
            System.out.println("***********************************************************************");
            TimeUnit.SECONDS.sleep(1);
            userInterface();
        } else if (choice.equals("2")) {
            System.out.println("To be implemented in future updates! Thank you for your patience");
            TimeUnit.SECONDS.sleep(1);
            userInterface();
        } else {
            System.out.println("\n****************************************");
            System.out.println("Invalid command choice! Please try again");
            System.out.println("****************************************");
            TimeUnit.SECONDS.sleep(1);
            selectCSVFileAsDatabase();
        }
    }
}