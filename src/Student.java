public class Student {
    private final String sid;
    private final String studentName;
    private final String birthDate;

    public Student(String sid, String studentName, String birthDate) {
        this.sid = sid;
        this.studentName = studentName;
        this.birthDate = birthDate;
    }

    public String getSID() { return sid; }

    public String getName() {
        return studentName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Student {\n" +
                "sid: " + sid + '\n' +
                "student name: " + studentName + '\n' +
                "date of birth: " + birthDate +
                "\n}";
    }
}
