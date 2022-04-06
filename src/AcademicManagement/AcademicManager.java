package AcademicManagement;

//Importing all objects and file handlers for processing
import Objects.Course;
import Objects.StudentEnrollment;
import Objects.Student;
import FileHandlers.CourseFileHandler;
import FileHandlers.EnrollmentFileHandler;
import FileHandlers.StudentFileHandler;

//Importing necessary libraries
import java.util.ArrayList;


public class AcademicManager implements StudentEnrollmentManager {

    //Initializing file handler objects
    private final StudentFileHandler studentFileHandler = new StudentFileHandler();
    private final CourseFileHandler courseFileHandler = new CourseFileHandler();
    private final EnrollmentFileHandler enrollmentFileHandler = new EnrollmentFileHandler();

    //Getters for writing to csv file if user chooses to save report
    public StudentFileHandler getStudentFileHandler() {
        return studentFileHandler;
    }

    public CourseFileHandler getCourseFileHandler() {
        return courseFileHandler;
    }

    //Calling the addEnrollment method
    @Override
    public boolean addNewEnrollment(StudentEnrollment e) {
        return enrollmentFileHandler.addOne(e);
    }

    //Calling the deleteEnrollment method
    @Override
    public boolean deleteExistingEnrollment(StudentEnrollment e) {
        return enrollmentFileHandler.deleteOne(e);
    }

    //Calling the getOneEnrollment method
    @Override
    public StudentEnrollment getOneEnrollment(String studentID, String courseID,
                                                         String semester) throws InterruptedException {
        return enrollmentFileHandler.populateOneEnrollmentFromData(studentID, courseID, semester);
    }

    //Returning the queried enrollment in string format for nicer display
    public String displayOneEnrollment(String studentID, String courseID,
                                       String semester) throws InterruptedException {
        return getOneEnrollment(studentID, courseID, semester).toString();
    }

    //Calling the getAllEnrollment method
    @Override
    public ArrayList<StudentEnrollment> getAllEnrollments() {
        return enrollmentFileHandler.populateEnrollmentData();
    }

    //Returning all enrollments in string format to remove square brackets & commas between elements
    public String displayAllEnrollments() {
        return getAllEnrollments().toString().replace("[", "").replace(", ", "\n".indent(-1))
                .replace("\n]", "");
    }

    //Calling the getOneStudent method
    public Student getOneStudent(String studentID) throws InterruptedException {
        return studentFileHandler.populateOneStudentFromData(studentID);
    }

    //Returning the queried student in string format for nicer display
    public String displayOneStudent(String studentID) throws InterruptedException {
        return getOneStudent(studentID).toString();
    }

    //Calling the getAllStudents method
    public ArrayList<Student> getAllStudents() {
        return studentFileHandler.populateStudentData();
    }

    //Returning all students in string format to remove square brackets & commas between elements
    public String displayAllStudents() {
        return getAllStudents().toString().replace("[", "").replace(", ", "\n".indent(-1))
                .replace("\n]", "");
    }

    //Calling the getOneCourse method
    public Course getOneCourse(String courseID) throws InterruptedException {
        return courseFileHandler.populateOneCourseFromData(courseID);
    }

    //Returning the queried course in string format for nicer display
    public String displayOneCourse(String courseID) throws InterruptedException {
        return getOneCourse(courseID).toString();
    }

    //Calling the getAllCourses method
    public ArrayList<Course> getAllCourses() {
        return courseFileHandler.populateCourseData();
    }

    //Returning all courses in string format to remove square brackets & commas between elements
    public String displayAllCourses() {
        return getAllCourses().toString().replace("[", "").replace(", ", "\n".indent(-1))
                .replace("\n]", "");
    }

    //Calling the getAllCoursesForStudentInSemester method
    public ArrayList<Course> getAllCourseForStudentInSemester() throws InterruptedException {
        ArrayList<Course> tempCourseList = enrollmentFileHandler.allCourseForStudentInSemester();
        courseFileHandler.setCourses(tempCourseList);
        return tempCourseList;
    }

    //Returning all courses for 1 student in 1 semester in string format to remove square brackets & commas between elements
    public String displayAllCoursesForStudentInSemester() throws InterruptedException {
        return getAllCourseForStudentInSemester().toString().replace("[", "")
                .replace(", ", "\n".indent(-1)).replace("\n]", "");
    }

    //Calling the getAllStudentsInCourseInSemester method
    public ArrayList<Student> getAllStudentsInCourseInSemester() throws InterruptedException {
        ArrayList<Student> tempStudentList = enrollmentFileHandler.allStudentsInCourseInSemester();
        studentFileHandler.setStudents(tempStudentList);
        return tempStudentList;
    }

    //Returning all students in 1 course in 1 semester in string format to remove square brackets & commas between elements
    public String displayAllStudentsInCourseInSemester() throws InterruptedException {
        return getAllStudentsInCourseInSemester().toString().replace("[", "")
                .replace(", ", "\n".indent(-1)).replace("\n]", "");
    }

    //Calling the getAllCoursesInSemester method
    public ArrayList<Course> getAllCoursesInSemester() throws InterruptedException {
        ArrayList<Course> tempCourseList = enrollmentFileHandler.allCoursesInSemester();
        courseFileHandler.setCourses(tempCourseList);
        return tempCourseList;
    }

    //Returning all courses in 1 semester in string format to remove square brackets & commas between elements
    public String displayAllCoursesInSemester() throws InterruptedException {
        return getAllCoursesInSemester().toString().replace("[", "")
                .replace(", ", "\n".indent(-1)).replace("\n]", "");
    }
}