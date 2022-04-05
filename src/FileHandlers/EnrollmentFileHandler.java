package FileHandlers;

import HelperPrograms.CSVReader;
import Objects.Course;
import Objects.Student;
import Objects.StudentEnrollment;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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

    public ArrayList<StudentEnrollment> populateOneEnrollmentFromData() throws InterruptedException {
        ArrayList<StudentEnrollment> allEnrollments = populateEnrollmentData();
        ArrayList<StudentEnrollment> singleEnrollment = new ArrayList<>();

        String studentID = gatherStudentFromUserInput();
        String courseID = gatherCourseFromUserInput();
        String semester = gatherSemesterFromUserInput();

        for (StudentEnrollment enrollment : allEnrollments) {
            if (enrollment.getStudent().getStudentID().equalsIgnoreCase(studentID) &&
                    enrollment.getCourse().getCourseID().equalsIgnoreCase(courseID) &&
                    enrollment.getSemester().equalsIgnoreCase(semester)) {
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
        TimeUnit.SECONDS.sleep(1);
        return singleEnrollment;
    }

    public boolean addOne() {
        ArrayList<StudentEnrollment> allEnrollments = populateEnrollmentData();

        String studentID = gatherStudentFromUserInput();
        String courseID = gatherCourseFromUserInput();
        String semester = gatherSemesterFromUserInput();

        for (StudentEnrollment enrollment : allEnrollments) {
            if (enrollment.getStudent().getStudentID().equalsIgnoreCase(studentID)
                    && enrollment.getCourse().getCourseID().equalsIgnoreCase(courseID)
                    && !enrollment.getSemester().equalsIgnoreCase(semester)) {
                allEnrollments.add(enrollment);
                System.out.println("Enrollment is successfully added!");
                return true;
            } else {
                System.out.println("This enrollment already exists/student or course is not in database!");
                return false;
            }
        }
        return false;
    }

    public boolean deleteOne() {
        ArrayList<StudentEnrollment> allEnrollments = populateEnrollmentData();

        String studentID = gatherStudentFromUserInput();
        String courseID = gatherCourseFromUserInput();
        String semester = gatherSemesterFromUserInput();

        for (StudentEnrollment enrollment : allEnrollments) {
            if (enrollment.getStudent().getStudentID().equalsIgnoreCase(studentID)
                    && enrollment.getCourse().getCourseID().equalsIgnoreCase(courseID)
                    && enrollment.getSemester().equalsIgnoreCase(semester)) {
                allEnrollments.remove(enrollment);
                System.out.println("Enrollment is successfully deleted!");
                return true;
            } else {
                System.out.println("This enrollment does not exist/student or course is not in database!");
                return false;
            }
        }
        return false;
    }

    public ArrayList<Course> allCourseForStudentInSemester() throws InterruptedException {
        ArrayList<StudentEnrollment> allEnrollments = populateEnrollmentData();
        ArrayList<Course> coursesForStudentInSemester = new ArrayList<>();

        String studentID = gatherStudentFromUserInput();
        String semester = gatherSemesterFromUserInput();

        for (StudentEnrollment enrollment : allEnrollments) {
            if (enrollment.getStudent().getStudentID().equalsIgnoreCase(studentID)
                    && enrollment.getSemester().equalsIgnoreCase(semester)) {
                coursesForStudentInSemester.add(enrollment.getCourse());
            }
        }
        System.out.println("\n***************************************************************************");
        System.out.println("Retrieving data of all courses from that student in the queried semester...");
        System.out.println("***************************************************************************\n");
        TimeUnit.SECONDS.sleep(1);
        return coursesForStudentInSemester;
    }

    public ArrayList<Student> allStudentsInCourseInSemester() throws InterruptedException {
        ArrayList<StudentEnrollment> allEnrollments = populateEnrollmentData();
        ArrayList<Student> studentsInCourseInSemester = new ArrayList<>();

        String courseID = gatherCourseFromUserInput();
        String semester = gatherSemesterFromUserInput();

        for (StudentEnrollment enrollment : allEnrollments) {
            if (enrollment.getCourse().getCourseID().equalsIgnoreCase(courseID)
                    && enrollment.getSemester().equalsIgnoreCase(semester))  {
                studentsInCourseInSemester.add(enrollment.getStudent());
            }
        }
        System.out.println("\n*************************************************************************");
        System.out.println("Retrieving data of all students in that course in the queried semester...");
        System.out.println("*************************************************************************\n");
        TimeUnit.SECONDS.sleep(1);
        return studentsInCourseInSemester;
    }

    public ArrayList<Course> allCoursesInSemester() throws InterruptedException {
        ArrayList<StudentEnrollment> allEnrollments = populateEnrollmentData();
        ArrayList<Course> coursesInSemester = new ArrayList<>();

        String semester = gatherSemesterFromUserInput();

        for (StudentEnrollment enrollment : allEnrollments) {
            if (!checkCourseDuplication(enrollment, coursesInSemester) &&
                    enrollment.getSemester().equalsIgnoreCase(semester)) {
                coursesInSemester.add(enrollment.getCourse());
            }
        }
        System.out.println("\n*********************************************************");
        System.out.println("Retrieving data of all courses in the queried semester...");
        System.out.println("*********************************************************\n");
        TimeUnit.SECONDS.sleep(1);
        return coursesInSemester;
    }

    private static boolean checkCourseDuplication(StudentEnrollment enrollment,
                                                  ArrayList<Course> coursesInSemester) {
        for (Course c : coursesInSemester) {
            if (c.getCourseID().equals(enrollment.getCourse().getCourseID())) {
                return true;
            }
        }
        return false;
    }

    public static String gatherStudentFromUserInput() {
        System.out.print("Please enter a student ID (ex. s000000): ");
        Scanner sc1 = new Scanner(System.in);

        return sc1.nextLine();
    }

    public static String gatherCourseFromUserInput() {
        System.out.print("Please enter a course ID (ex. COSC0000): ");
        Scanner sc2 = new Scanner(System.in);

        return sc2.nextLine();
    }

    public static String gatherSemesterFromUserInput() {
        System.out.print("Please enter a semester (ex. 2022A): ");
        Scanner sc3 = new Scanner(System.in);

        return sc3.nextLine();
    }
}

