package Objects;

public class Student implements Object {

    //Initializing object variables
    private final String studentID;
    private final String studentName;
    private final String birthDate;

    //Constructor
    public Student(String studentID, String studentName, String birthDate) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.birthDate = birthDate;
    }

    //Getters for each variable
    public String getStudentID() { return this.studentID; }

    public String getStudentName() {
        return this.studentName;
    }

    public String getBirthDate() { return this.birthDate; }

    @Override

    //This method converts a student object to a string for processing
    public String toString() {
        return "- Student Information {\n" + "+ student id: " + studentID + "\n" +
                "+ student name: " + studentName + "\n" + "+ date of birth (MM/DD/YYYY): " + birthDate + "\n}" + "\n";
    }

    @Override

    //This method converts a student object to a string readable & writeable to a csv file later
    public String convertToCSVRow() {
        return String.join(",", studentID, studentName, birthDate + "\n");
    }
}
