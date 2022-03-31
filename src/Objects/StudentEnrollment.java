package Objects;

public class StudentEnrollment {
    private final String studentName;
    private final String courseName;
    private final String semester;

    public StudentEnrollment(String studentName, String courseName, String semester) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.semester = semester;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return "Enrollment Information {\n" + "+ student name: " + studentName + "\n" +
                "+ course name: " + courseName + "\n" + "+ semester code: " + semester + "\n}" + "\n";
    }

    public String convertToCSVRow() {
        return String.join(",", studentName, courseName, semester + "\n");
    }
}
