package FileHandlers;

import HelperPrograms.CSVReader;
import Objects.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentFileHandler {

    public ArrayList<Student> populateStudentData() {
        ArrayList<Student> listOfStudents = new ArrayList<>();
        CSVReader reader = new CSVReader();
        ArrayList<String> dataList = new ArrayList<>(reader.readCSVFile());

        for (String studentInfo : dataList) {
            String[] data = studentInfo.split(",");
            String studentID = data[0];
            String studentName = data[1];
            String birthDate = data[2];

            Student student = new Student(studentID, studentName, birthDate);

            if (!studentAlreadyExists(studentID, studentName, birthDate, listOfStudents)) {
                listOfStudents.add(student);
            }
        }
        return listOfStudents;
    }

    private static boolean studentAlreadyExists(String studentID, String studentName,
                                               String birthDate, List<Student> listOfStudents) {
        for (Student s : listOfStudents) {
            if (s.getStudentID().equals(studentID) ||
                    (s.getStudentName().equals(studentName)) && s.getBirthDate().equals(birthDate)) {
                return true;
            }
        }
        return false;
    }
}
