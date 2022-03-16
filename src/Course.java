public class Course {
    private final String cid;
    private final String courseName;
    private final int numberOfCredits;

    public Course(String cid, String courseName, int numberOfCredits) {
        this.cid = cid;
        this.courseName = courseName;
        this.numberOfCredits = numberOfCredits;
    }

    public String getCID() {
        return cid;
    }

    public String getName() {
        return courseName;
    }

    public int getNumberOfCredits() { return numberOfCredits; }

    @Override
    public String toString() {
        return "Course {\n" +
                "cid: " + cid + '\n' +
                "course name: " + courseName + '\n' +
                "number of credits: " + numberOfCredits +
                "\n}";
    }
}
