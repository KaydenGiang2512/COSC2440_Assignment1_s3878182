package Objects;

public class Course {
    private final String courseID;
    private final String courseName;
    private final int numberOfCredits;

    public Course(String courseID, String courseName, int numberOfCredits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.numberOfCredits = numberOfCredits;
    }

    public String getCourseID() {
        return this.courseID;
    }

    public String getCourseName() { return this.courseName; }

    public int getNumberOfCredits() { return this.numberOfCredits; }

    @Override
    public String toString() {
        return "Course Information {\n" + "+ course id: " + courseID + "\n" +
                "+ course name: " + courseName + "\n" + "+ number of credits: " + numberOfCredits + "\n}" + "\n";
    }
}
