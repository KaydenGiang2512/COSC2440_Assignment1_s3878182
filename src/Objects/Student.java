package Objects;

public class Student {
    private final String studentID;
    private final String studentName;
    private final String birthDate;

    public Student(String studentID, String studentName, String birthDate) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.birthDate = birthDate;
    }

    public String getStudentID() { return this.studentID; }

    public String getStudentName() {
        return this.studentName;
    }

    public String getBirthDate() { return this.birthDate; }

    @Override
    public String toString() {
        return "- Student Information {\n" + "+ student id: " + studentID + "\n" +
                "+ student name: " + studentName + "\n" + "+ date of birth (MM/DD/YYYY): " + birthDate + "\n}" + "\n";
    }

    public String convertToCSVRow() {
        return String.join(",", studentID,
                studentName, birthDate + "\n");
    }
}
