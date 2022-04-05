package AcademicManagement;

import Objects.Course;
import Objects.StudentEnrollment;
import Objects.Student;
import FileHandlers.CourseFileHandler;
import FileHandlers.EnrollmentFileHandler;
import FileHandlers.StudentFileHandler;

import java.util.ArrayList;

public class AcademicManager implements StudentEnrollmentManager {
    @Override
    public void addNewEnrollment() {

    }

    @Override
    public void deleteEnrollment() {

    }

    @Override
    public ArrayList<StudentEnrollment> getOneEnrollment() {
        EnrollmentFileHandler efh = new EnrollmentFileHandler();
        return efh.populateOneEnrollmentFromData();
    }

    public String displayOneEnrollment() {
        return getOneEnrollment().toString().
                replace("[", "").replace("\n]", "");
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
}
