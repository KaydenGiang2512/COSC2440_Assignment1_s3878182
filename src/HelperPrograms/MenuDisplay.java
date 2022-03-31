package HelperPrograms;

import FileHandlers.CourseFileHandler;
import FileHandlers.EnrollmentFileHandler;
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
                    StudentFileHandler s = new StudentFileHandler();
                    System.out.println(s.displayAllStudents());
                    isRunning = false;
                    TimeUnit.SECONDS.sleep(1);
                    restartProgram();
                }
                case "2" -> {
                    System.out.println("\n*********************************");
                    System.out.println("Retrieving all data of courses...");
                    System.out.println("*********************************");
                    TimeUnit.SECONDS.sleep(1);
                    CourseFileHandler c = new CourseFileHandler();
                    System.out.println(c.displayAllCourses());
                    isRunning = false;
                    TimeUnit.SECONDS.sleep(1);
                    restartProgram();
                }
                case "3" -> {
                    System.out.println("\n*************************************");
                    System.out.println("Retrieving all data of enrollments...");
                    System.out.println("*************************************");
                    TimeUnit.SECONDS.sleep(1);
                    EnrollmentFileHandler e = new EnrollmentFileHandler();
                    System.out.println(e.displayAllEnrollments());
                    isRunning = false;
                    TimeUnit.SECONDS.sleep(1);
                    restartProgram();
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

    public static void quitProgram () throws InterruptedException {
        System.out.println("\n*****************************************");
        System.out.println("Exiting program... goodbye Administrator!");
        System.out.println("*****************************************");
        TimeUnit.SECONDS.sleep(1);
        System.exit(0);
    }

    public static void restartProgram () throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.print("\nDo you wish to run this program again? (Y/N): ");
        Scanner sc = new Scanner(System.in);
        String restartChoice = sc.nextLine();

        if (restartChoice.equalsIgnoreCase("y")) {
            System.out.println("\n**************************************");
            System.out.println("Restarting program... Please stand by!");
            System.out.println("**************************************");
            TimeUnit.SECONDS.sleep(1);
            userInterface();
        } else if (restartChoice.equalsIgnoreCase("n")) {
            quitProgram();
        } else {
            System.out.println("\n****************************************");
            System.out.println("Invalid command choice! Please try again");
            System.out.println("****************************************");
            TimeUnit.SECONDS.sleep(1);
            restartProgram();
        }
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
            System.out.println("\n********************************************");
            System.out.println("nothing implemented yet. Come back later :))");
            System.out.println("********************************************");
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
