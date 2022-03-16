import java.util.List;

public interface StudentEnrollmentManager {
    void addNewStudent(Student student);

    void addNewCourse(Course course);

    void addNewEnrollment(String sid, String cid, String semester);

    void deleteCurrentEnrollment(String sid, String cid, String semester);

    Student getStudent(String sid);

    Course getCourse(String cid);

    StudentEnrollment getEnrollment(String sid, String cid, String semester);

    List<Student> getAllStudents();

    List<Course> getAllCourses();

    List<StudentEnrollment> getAllEnrollments();
}
