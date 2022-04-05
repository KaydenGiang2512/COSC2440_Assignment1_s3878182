package AcademicManagement;

import Objects.StudentEnrollment;
import java.util.ArrayList;

public interface StudentEnrollmentManager {

    void addNewEnrollment();

    void deleteEnrollment();

    ArrayList<StudentEnrollment> getOneEnrollment();

    ArrayList<StudentEnrollment> getAllEnrollments();
}
