package FileHandlers;

import HelperPrograms.CSVReader;
import Objects.Course;
import Objects.Student;
import Objects.StudentEnrollment;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentFileHandler {
    public ArrayList<StudentEnrollment> populateEnrollmentData() {
        ArrayList<StudentEnrollment> listOfEnrollments = new ArrayList<>();
        CSVReader reader = new CSVReader("default.csv");

        for (String enrollmentInfo : reader.readCSVFile()) {
            String[] data = enrollmentInfo.split(",");
            Student student = new Student(data[0], data[1], data[2]);
            Course course = new Course(data[3], data[4], Integer.parseInt(data[5]));
            String semester = data[6];

            StudentEnrollment enrollment = new StudentEnrollment(student, course, semester);

            if (!enrollmentAlreadyExist(student, course, semester, listOfEnrollments)) {
                listOfEnrollments.add(enrollment);
            }
        }
        return listOfEnrollments;
    }

    private static boolean enrollmentAlreadyExist(Student student, Course course,
                                                  String semester, List<StudentEnrollment> listOfEnrollments) {
        for (StudentEnrollment e : listOfEnrollments) {
            if (e.getStudent().equals(student) &&
                    e.getCourse().equals(course) && e.getSemester().equals(semester)) {
                return true;
            }
        }
        return false;
    }

    public String displayAllEnrollments() {
        return populateEnrollmentData().toString().replace("[", "").replace(", ", "\n".indent(-1))
                .replace("\n]", "");
    }
}
