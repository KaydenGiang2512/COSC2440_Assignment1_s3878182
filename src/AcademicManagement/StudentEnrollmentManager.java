package AcademicManagement;

import Objects.StudentEnrollment;
import java.util.ArrayList;

public interface StudentEnrollmentManager {

    void addNewEnrollment(String studentID, String courseID, String semester);

    void deleteEnrollment(String studentID, String courseID, String semester);

    StudentEnrollment getOneEnrollment(String studentID, String courseID, String semester);

    ArrayList<StudentEnrollment> getAllEnrollments();
}
