package Objects;

public class StudentEnrollment {
    private final String student;
    private final String course;
    private final String semester;

    public StudentEnrollment(String student, String course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public String getStudent() {
        return student;
    }

    public String getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return "Enrollment Information {\n" +
                "student name: " + student + "\n" +
                "course name: " + course + "\n" +
                "semester code: " + semester +
                "\n}" + "\n";
    }
}
