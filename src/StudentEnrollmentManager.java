import java.util.List;

public interface StudentEnrollmentManager {
    List<Student> getAllStudents();

    List<Course> getAllCourses();

    List<StudentEnrollment> getAllEnrollments();
}
