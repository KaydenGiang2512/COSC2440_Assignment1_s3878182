package FileHandlers;

import HelperPrograms.CSVReader;
import Objects.Course;
import Objects.Student;
import Objects.StudentEnrollment;
import FileHandlers.StudentFileHandler;
import FileHandlers.CourseFileHandler;

import java.util.ArrayList;
import java.util.Scanner;

public class EnrollmentFileHandler {
    public ArrayList<StudentEnrollment> populateEnrollmentData() {
        ArrayList<StudentEnrollment> listOfEnrollments = new ArrayList<>();
        CSVReader reader = new CSVReader();
        ArrayList<String> dataList = new ArrayList<>(reader.readCSVFile());

        for (String enrollmentInfo : dataList) {
            String[] data = enrollmentInfo.split(",");
            Student student = new Student(data[0], data[1], data[2]);
            Course course = new Course(data[3], data[4], Integer.parseInt(data[5]));
            String semester = data[6];

            StudentEnrollment enrollment = new StudentEnrollment(student, course, semester);

            if (!enrollmentAlreadyExists(student, course, semester, listOfEnrollments)) {
                listOfEnrollments.add(enrollment);
            }
        }
        return listOfEnrollments;
    }

    private static boolean enrollmentAlreadyExists(Student student, Course course,
                                                  String semester, ArrayList<StudentEnrollment> listOfEnrollments) {
        for (StudentEnrollment e : listOfEnrollments) {
            if (e.getStudent().equals(student) &&
                    e.getCourse().equals(course) && e.getSemester().equals(semester)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<StudentEnrollment> populateOneEnrollmentFromData() {
        ArrayList<StudentEnrollment> allEnrollments = populateEnrollmentData();
        ArrayList<StudentEnrollment> singleEnrollment = new ArrayList<>();
        ArrayList<String> userInputs = gatherEnrollmentFromUserInput();

        for (StudentEnrollment enrollment : allEnrollments) {
            if (enrollment.getStudent().getStudentID().equalsIgnoreCase(userInputs.get(0)) &&
                    enrollment.getCourse().getCourseID().equalsIgnoreCase(userInputs.get(1)) &&
                    enrollment.getSemester().equalsIgnoreCase(userInputs.get(2))) {
                singleEnrollment.add(enrollment);
            }
        }

        if (singleEnrollment.isEmpty()) {
            System.out.println("Your search yielded no results! Please check all information and try again");
            populateOneEnrollmentFromData();
        }
        System.out.println("\n********************************************");
        System.out.println("Retrieving data of the queried enrollment...");
        System.out.println("********************************************\n");
        return singleEnrollment;
    }

//    public boolean addOne() {
//        ArrayList<StudentEnrollment> allEnrollments = populateEnrollmentData();
//        ArrayList<String> userInputs = gatherEnrollmentFromUserInput();
//
//        for (StudentEnrollment enrollment : allEnrollments) {
//            if (enrollment.getStudent().getStudentID().equalsIgnoreCase(userInputs.get(0)) &&
//            enrollment.getCourse().getCourseID().equalsIgnoreCase(userInputs.get(1)) ) {
//
//            }
//        }
//        return true;
//    }

//    public ArrayList<Course> allCoursesInSemester() {
//        ArrayList<StudentEnrollment> allEnrollments = populateEnrollmentData();
//
//
//    }

    public static ArrayList<String> gatherEnrollmentFromUserInput() {
        ArrayList<String> inputs = new ArrayList<>();

        System.out.print("Please enter a student ID (ex. s000000): ");
        Scanner sc1 = new Scanner(System.in);
        String studentID = sc1.nextLine();
        System.out.print("Please enter a course ID (ex. COSC0000): ");
        Scanner sc2 = new Scanner(System.in);
        String courseID = sc2.nextLine();
        System.out.print("Please enter a semester (ex. 2022A): ");
        Scanner sc3 = new Scanner(System.in);
        String semester = sc3.nextLine();

        inputs.add(studentID);
        inputs.add(courseID);
        inputs.add(semester);

        return inputs;
    }
}

