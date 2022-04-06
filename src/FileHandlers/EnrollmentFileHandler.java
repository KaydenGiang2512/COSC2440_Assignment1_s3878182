package FileHandlers;

//Importing necessary packages
import HelperPrograms.CSVReader;
import HelperPrograms.MenuDisplay;
import Objects.Course;
import Objects.Student;
import Objects.StudentEnrollment;

//Importing necessary libraries
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class EnrollmentFileHandler {

    //Creating an ArrayList variable that stores all enrollment data for shared use
    private final ArrayList<StudentEnrollment> allEnrollments = populateEnrollmentData();

    //This method populates all enrollment-related data from the default.csv file, and stores them in an ArrayList
    public ArrayList<StudentEnrollment> populateEnrollmentData() {
        CSVReader reader = new CSVReader();
        ArrayList<StudentEnrollment> listOfEnrollments = new ArrayList<>();
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

    //This method retrieves an enrollment object from the enrollment ArrayList
    public StudentEnrollment populateOneEnrollmentFromData(String studentID, String courseID,
                                                           String semester) throws InterruptedException {
        ArrayList<StudentEnrollment> singleEnrollment = new ArrayList<>();

        studentID = gatherStudentFromUserInput();
        courseID = gatherCourseFromUserInput();
        semester = gatherSemesterFromUserInput();

        for (StudentEnrollment enrollment : allEnrollments) {
            if (enrollment.getStudent().getStudentID().equalsIgnoreCase(studentID) &&
                    enrollment.getCourse().getCourseID().equalsIgnoreCase(courseID) &&
                    enrollment.getSemester().equalsIgnoreCase(semester)) {
                singleEnrollment.add(enrollment);
            }
        }
        if (singleEnrollment.isEmpty()) {
            System.out.println("Your search yielded no results! Please check all information and try again");
            MenuDisplay.userInterface();
        }
        System.out.println("\n********************************************");
        System.out.println("Retrieving data of the queried enrollment...");
        System.out.println("********************************************\n");
        TimeUnit.SECONDS.sleep(1);
        return singleEnrollment.get(0);
    }

    //Checker to determine if the enrollment is already added to the enrollment ArrayList (to avoid duplication)
    private boolean enrollmentAlreadyExists(Student student, Course course,
                                                   String semester, ArrayList<StudentEnrollment> listOfEnrollments) {
        for (StudentEnrollment e : listOfEnrollments) {
            if (e.getStudent().equals(student) &&
                    e.getCourse().equals(course) && e.getSemester().equals(semester)) {
                return true;
            }
        }
        return false;
    }

    //This method takes in 3 strings (studentID, courseID, semester) and retrieves an enrollment accordingly
    public boolean addOne(StudentEnrollment e) {
        String studentID = gatherStudentFromUserInput();
        String courseID = gatherCourseFromUserInput();
        String semester = gatherSemesterFromUserInput();

        for (StudentEnrollment enrollment : allEnrollments) {
            if (enrollment.getStudent().getStudentID().equalsIgnoreCase(studentID)
                && enrollment.getCourse().getCourseID().equalsIgnoreCase(courseID)) {
                if (!enrollment.getSemester().equalsIgnoreCase(semester)) {
                    enrollment = new StudentEnrollment(enrollment.getStudent(), enrollment.getCourse(), semester);
                    allEnrollments.add(enrollment);
                    System.out.println("Enrollment is successfully added!");
                    return true;
                }
            } else {
                System.out.println("This enrollment already exists/student or course is not in database!");
                return false;
            }
        }
        return false;
    }

    //This method takes in 3 strings (studentID, courseID, semester) and removes an enrollment accordingly
    public boolean deleteOne(StudentEnrollment e) {
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

    //This method takes in 2 strings (studentID, semester) and returns an ArrayList containing objects accordingly
    public ArrayList<Course> allCourseForStudentInSemester() throws InterruptedException {
        ArrayList<Course> coursesForStudentInSemester = new ArrayList<>();

        String studentID = gatherStudentFromUserInput();
        String semester = gatherSemesterFromUserInput();

        for (StudentEnrollment enrollment : allEnrollments) {
            if (enrollment.getStudent().getStudentID().equalsIgnoreCase(studentID)
                    && enrollment.getSemester().equalsIgnoreCase(semester)) {
                coursesForStudentInSemester.add(enrollment.getCourse());
            }
        }
        if (coursesForStudentInSemester.isEmpty()) {
            System.out.println("This student is not enrolled in any courses for this semester!");
        }
        System.out.println("\n***************************************************************************");
        System.out.println("Retrieving data of all courses from that student in the queried semester...");
        System.out.println("***************************************************************************\n");
        TimeUnit.SECONDS.sleep(1);
        return coursesForStudentInSemester;
    }

    //This method takes in 2 strings (courseID, semester) and returns an ArrayList containing objects accordingly
    public ArrayList<Student> allStudentsInCourseInSemester() throws InterruptedException {
        ArrayList<Student> studentsInCourseInSemester = new ArrayList<>();

        String courseID = gatherCourseFromUserInput();
        String semester = gatherSemesterFromUserInput();

        for (StudentEnrollment enrollment : allEnrollments) {
            if (enrollment.getCourse().getCourseID().equalsIgnoreCase(courseID)
                    && enrollment.getSemester().equalsIgnoreCase(semester))  {
                studentsInCourseInSemester.add(enrollment.getStudent());
            }
        }
        if (studentsInCourseInSemester.isEmpty()) {
            System.out.println("There are no students enrolled in this course for this semester!");
        }
        System.out.println("\n*************************************************************************");
        System.out.println("Retrieving data of all students in that course in the queried semester...");
        System.out.println("*************************************************************************\n");
        TimeUnit.SECONDS.sleep(1);
        return studentsInCourseInSemester;
    }

    //This method takes in 1 string (semester) and returns an ArrayList containing objects accordingly
    public ArrayList<Course> allCoursesInSemester() throws InterruptedException {
        ArrayList<Course> coursesInSemester = new ArrayList<>();

        String semester = gatherSemesterFromUserInput();

        for (StudentEnrollment enrollment : allEnrollments) {
            if (!checkCourseDuplication(enrollment, coursesInSemester) &&
                    enrollment.getSemester().equalsIgnoreCase(semester)) {
                coursesInSemester.add(enrollment.getCourse());
            }
        }
        if (coursesInSemester.isEmpty()) {
            System.out.println("There no available courses in this semester!");
        }
        System.out.println("\n*********************************************************");
        System.out.println("Retrieving data of all courses in the queried semester...");
        System.out.println("*********************************************************\n");
        TimeUnit.SECONDS.sleep(1);
        return coursesInSemester;
    }

    //Checker for allCoursesInSemester method
    private static boolean checkCourseDuplication(StudentEnrollment enrollment,
                                                  ArrayList<Course> coursesInSemester) {
        for (Course c : coursesInSemester) {
            if (c.getCourseID().equals(enrollment.getCourse().getCourseID())) {
                return true;
            }
        }
        return false;
    }

    //Get user inputs of 3 strings (studentID, courseID, semester) for shared use
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