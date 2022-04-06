package Objects;

public class Course implements Object {

    //Initializing object variables
    private final String courseID;
    private final String courseName;
    private final int numberOfCredits;

    //Constructor
    public Course(String courseID, String courseName, int numberOfCredits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.numberOfCredits = numberOfCredits;
    }

    //Getters for each variable
    public String getCourseID() {
        return this.courseID;
    }

    public String getCourseName() { return this.courseName; }

    public int getNumberOfCredits() { return this.numberOfCredits; }

    @Override

    //This method converts a course object to a string for processing
    public String toString() {
        return "- Course Information {\n" + "+ course id: " + courseID + "\n" +
                "+ course name: " + courseName + "\n" + "+ number of credits: " + numberOfCredits + "\n}" + "\n";
    }

    @Override

    //This method converts a course object to a string readable & writeable to a csv file later
    public String convertToCSVRow() {
        return String.join(",", courseID, courseName, numberOfCredits + "\n");
    }
}
