package FileHandlers;

import HelperPrograms.CSVReader;
import Objects.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseFileHandler {
    public ArrayList<Course> populateCourseData() {
        ArrayList<Course> listOfCourses = new ArrayList<>();
        CSVReader reader = new CSVReader("default.csv");

        for (String courseInfo : reader.readCSVFile()) {
            String[] data = courseInfo.split(",");
            String courseID = data[3];
            String courseName = data[4];
            int numberOfCredits = Integer.parseInt(data[5]);

            Course course = new Course(courseID, courseName, numberOfCredits);

            if (!courseAlreadyExist(courseID, courseName, numberOfCredits, listOfCourses)) {
                listOfCourses.add(course);
            }
        }
        return listOfCourses;
    }

    private static boolean courseAlreadyExist(String courseID, String courseName,
                                              int numberOfCredits, List<Course> listOfCourses) {
        for (Course c : listOfCourses) {
            if (c.getCourseID().equals(courseID) ||
                    (c.getCourseName().equals(courseName) && c.getNumberOfCredits() == numberOfCredits)) {
                return true;
            }
        }
        return false;
    }
}
