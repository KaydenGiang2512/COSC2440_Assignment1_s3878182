package AcademicManagement;

import Objects.StudentEnrollment;
import java.util.ArrayList;

public interface StudentEnrollmentManager {

    //Defining the 4 methods in the interface to be implemented
    boolean addNewEnrollment(StudentEnrollment e);

    boolean deleteExistingEnrollment(StudentEnrollment e);

    StudentEnrollment getOneEnrollment(String studentID, String courseID, String semester) throws InterruptedException;

    ArrayList<StudentEnrollment> getAllEnrollments();
}
