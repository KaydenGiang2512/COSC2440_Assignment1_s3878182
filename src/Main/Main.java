package Main;

import Objects.Student;
import Objects.Course;
import HelperPrograms.CSVReader;

public class Main {
    public static void main(String[] args) {
        for (String studentInfo : CSVReader.readCSVFile()) {
            String[] data = studentInfo.split(",");
            Student student = new Student(data[0], data[1], data[2]);
            Course course = new Course(data[3], data[4], Integer.parseInt(data[5]));
//            System.out.println(student);
            System.out.println(course);
        }
    }
}
