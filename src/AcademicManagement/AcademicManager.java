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
    public boolean addNewEnrollment() {
        EnrollmentFileHandler efh = new EnrollmentFileHandler();
        return efh.addOne();
    }

    @Override
    public boolean deleteExistingEnrollment() {
        EnrollmentFileHandler efh = new EnrollmentFileHandler();
        return efh.deleteOne();
    }

    @Override
    public ArrayList<StudentEnrollment> getOneEnrollment() throws InterruptedException {
        EnrollmentFileHandler efh = new EnrollmentFileHandler();
        return efh.populateOneEnrollmentFromData();
    }

    public String displayOneEnrollment() throws InterruptedException {
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

    public ArrayList<Course> getAllCoursesForStudentInSemester() throws InterruptedException {
        EnrollmentFileHandler efh = new EnrollmentFileHandler();
        return efh.allCourseForStudentInSemester();
    }

    public String displayAllCoursesForStudentInSemester() throws InterruptedException {
        return getAllCoursesForStudentInSemester().toString().replace("[", "")
                .replace(", ", "\n".indent(-1)).replace("\n]", "");
    }

    public ArrayList<Student> getAllStudentsInCourseInSemester() throws InterruptedException {
        EnrollmentFileHandler efh = new EnrollmentFileHandler();
        return efh.allStudentsInCourseInSemester();
    }

    public String displayAllStudentsInCourseInSemester() throws InterruptedException {
        return getAllStudentsInCourseInSemester().toString().replace("[", "")
                .replace(", ", "\n".indent(-1)).replace("\n]", "");
    }

    public ArrayList<Course> getAllCoursesInSemester() throws InterruptedException {
        EnrollmentFileHandler efh = new EnrollmentFileHandler();
        return efh.allCoursesInSemester();
    }

    public String displayAllCoursesInSemester() throws InterruptedException {
        return getAllCoursesInSemester().toString().replace("[", "")
                .replace(", ", "\n".indent(-1)).replace("\n]", "");
    }
}
