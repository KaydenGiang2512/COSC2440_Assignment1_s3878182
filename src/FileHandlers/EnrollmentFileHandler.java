package FileHandlers;

import HelperPrograms.CSVReader;
import Objects.StudentEnrollment;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentFileHandler {
    public String populateEnrollmentData() {
        ArrayList<StudentEnrollment> listOfEnrollments = new ArrayList<>();
        CSVReader reader = new CSVReader("default.csv");

        for (String enrollmentInfo : reader.readCSVFile()) {
            String[] data = enrollmentInfo.split(",");
            String studentName = data[1];
            String courseName = data[4];
            String semester = data[6];

            StudentEnrollment enrollment = new StudentEnrollment(studentName, courseName, semester);

            if (!enrollmentAlreadyExist(studentName, courseName, semester, listOfEnrollments)) {
                listOfEnrollments.add(enrollment);
            }
        }
        return listOfEnrollments.toString().replace("[", "").replace(", ", "".indent(-1))
                .replace("\n]", "");
    }

    private static boolean enrollmentAlreadyExist(String studentName, String courseName,
                                               String semester, List<StudentEnrollment> listOfEnrollments) {
        for (StudentEnrollment e : listOfEnrollments) {
            if (e.getStudentName().equals(studentName) &&
                    e.getCourseName().equals(courseName) && e.getSemester().equals(semester)) {
                return true;
            }
        }
        return false;
    }
}
