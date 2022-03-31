package AcademicManagement;

import Objects.StudentEnrollment;
import java.util.List;

public interface StudentEnrollmentManager {

    void addNewEnrollment(String studentID, String courseID, String semester);

    void deleteEnrollment(String studentID, String courseID, String semester);

    StudentEnrollment getOneEnrollment(String studentID, String courseID, String semester);

    List<StudentEnrollment> getAllEnrollments();
}
