package AcademicManagement;

import Objects.Course;
import Objects.StudentEnrollment;
import Objects.Student;
import FileHandlers.CourseFileHandler;
import FileHandlers.EnrollmentFileHandler;
import FileHandlers.StudentFileHandler;
import HelperPrograms.CSVReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AcademicManager implements StudentEnrollmentManager {
    @Override
    public void addNewEnrollment(String studentID, String courseID, String semester) {

    }

    @Override
    public void deleteEnrollment(String studentID, String courseID, String semester) {

    }

    @Override
    public StudentEnrollment getOneEnrollment(String studentID, String courseID, String semester) {
        return null;
    }

    @Override
    public ArrayList<StudentEnrollment> getAllEnrollments() {
        EnrollmentFileHandler efh = new EnrollmentFileHandler();
        return efh.populateEnrollmentData();
    }

    public String displayAllEnrollments() {
        return getAllEnrollments().toString().replace("[", "").replace(", ", "\n".indent(-1))
                .replace("\n]", "");
    }

    public ArrayList<Student> getAllStudents() {
        StudentFileHandler sfh = new StudentFileHandler();
        return sfh.populateStudentData();
    }

    public String displayAllStudents() {
        return getAllStudents().toString().replace("[", "").replace(", ", "\n".indent(-1))
                .replace("\n]", "");
    }

    public ArrayList<Course> getAllCourses() {
        CourseFileHandler cfh = new CourseFileHandler();
        return cfh.populateCourseData();
    }

    public String displayAllCourses() {
        return getAllCourses().toString().replace("[", "").replace(", ", "\n".indent(-1))
                .replace("\n]", "");
    }

    public boolean populateFromImportedDatabase(String fileName) {
        System.out.print("\nPlease enter database file name (including file type): ");
        Scanner sc = new Scanner(System.in);
        fileName = sc.nextLine();
        CSVReader reader = new CSVReader(fileName);
        reader.readCSVFile();
        StudentFileHandler sfh = new StudentFileHandler();
        CourseFileHandler cfh = new CourseFileHandler();
        EnrollmentFileHandler efh = new EnrollmentFileHandler();
        System.out.println("\n************************************************");
        System.out.println("Database file successfully imported & processed!");
        System.out.println("************************************************");
        return true;
    }
}
