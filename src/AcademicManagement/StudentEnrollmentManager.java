package AcademicManagement;

import Objects.StudentEnrollment;
import java.util.ArrayList;

public interface StudentEnrollmentManager {

    boolean addNewEnrollment();

    boolean deleteExistingEnrollment();

    ArrayList<StudentEnrollment> getOneEnrollment() throws InterruptedException;

    ArrayList<StudentEnrollment> getAllEnrollments();
}
