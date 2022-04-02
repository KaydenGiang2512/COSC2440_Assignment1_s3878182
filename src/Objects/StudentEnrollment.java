package Objects;

public class StudentEnrollment {
    private final Student student;
    private final Course course;
    private final String semester;

    public StudentEnrollment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return "Enrollment Information {\n" + "\n" + student.toString().indent(2) + "\n" + course.toString().indent(2)
                + "\n" + "  - Semester code: " + semester + "\n}" + "\n";
    }

    public String convertToCSVRow() {
        return String.join(",", student.getStudentID(), student.getStudentName(),
                student.getBirthDate(), course.getCourseID(), course.getCourseName(),
                Integer.toString(course.getNumberOfCredits()), semester + "\n");
    }
}
