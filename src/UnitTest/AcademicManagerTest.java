package UnitTest;

import AcademicManagement.AcademicManager;
import FileHandlers.EnrollmentFileHandler;
import Objects.StudentEnrollment;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class AcademicManagerTest {
    private final AcademicManager admin = new AcademicManager();
    private final EnrollmentFileHandler enrollmentFileHandler = new EnrollmentFileHandler();

    @BeforeEach
    void initialize() {
        enrollmentFileHandler.populateEnrollmentData();
    }

    //Testing the addEnrollment method
    @org.junit.jupiter.api.Test
    void addNewEnrollment() throws InterruptedException {
        StudentEnrollment enrollment1 = new StudentEnrollment(admin.getOneStudent("s101312"),
                admin.getOneCourse("cosc4030"), "2020c");
        assertFalse(admin.addNewEnrollment(enrollment1));
        StudentEnrollment enrollment2 = new StudentEnrollment(admin.getOneStudent("s103723"),
                admin.getOneCourse("bus2232"), "2021a");
        assertTrue(admin.addNewEnrollment(enrollment2));
        StudentEnrollment enrollment3 = new StudentEnrollment(admin.getOneStudent("s101163"),
                admin.getOneCourse("cosc3321"), "2021a");
        assertFalse(admin.addNewEnrollment(enrollment3));
        StudentEnrollment enrollment4 = new StudentEnrollment(admin.getOneStudent("s102732"),
                admin.getOneCourse("cosc4030"), "2020b");
        assertTrue(admin.addNewEnrollment(enrollment4));
        StudentEnrollment enrollment5 = new StudentEnrollment(admin.getOneStudent("s102732"),
                admin.getOneCourse("cosc4030"), "2020c");
        assertFalse(admin.deleteExistingEnrollment(enrollment5));
        StudentEnrollment enrollment6 = new StudentEnrollment(admin.getOneStudent("s103817"),
                admin.getOneCourse("cosc4030"), "2021a");
        assertTrue(admin.addNewEnrollment(enrollment6));
    }

    //Testing the deleteEnrollment method
    @org.junit.jupiter.api.Test
    void deleteExistingEnrollment() throws InterruptedException {
        StudentEnrollment enrollment1 = new StudentEnrollment(admin.getOneStudent("s101312"),
                admin.getOneCourse("cosc4030"), "2020c");
        assertTrue(admin.deleteExistingEnrollment(enrollment1));
        StudentEnrollment enrollment2 = new StudentEnrollment(admin.getOneStudent("s103723"),
                admin.getOneCourse("bus2232"), "2021a");
        assertFalse(admin.deleteExistingEnrollment(enrollment2));
        StudentEnrollment enrollment3 = new StudentEnrollment(admin.getOneStudent("s101163"),
                admin.getOneCourse("cosc3321"), "2021a");
        assertTrue(admin.deleteExistingEnrollment(enrollment3));
        StudentEnrollment enrollment4 = new StudentEnrollment(admin.getOneStudent("s102732"),
                admin.getOneCourse("cosc4030"), "2020b");
        assertFalse(admin.deleteExistingEnrollment(enrollment4));
        StudentEnrollment enrollment5 = new StudentEnrollment(admin.getOneStudent("s102192"),
                admin.getOneCourse("phys1230"), "2021a");
        assertTrue(admin.addNewEnrollment(enrollment5));
        StudentEnrollment enrollment6 = new StudentEnrollment(admin.getOneStudent("s103912"),
                admin.getOneCourse("cosc3321"), "2020c");
        assertFalse(admin.addNewEnrollment(enrollment6));
    }

    // Testing the getOneEnrollment method
    @org.junit.jupiter.api.Test
    void getOneEnrollment() throws InterruptedException {
        assertNotNull(admin.getOneEnrollment("s101312", "cosc4030", "2020c"));
        assertNull(admin.getOneEnrollment("s101312", "cosc4030", "2022r"));
        assertNotNull(admin.getOneEnrollment("s102732", "cosc4030", "2020c"));
        assertNull(admin.getOneEnrollment("s102372", "bus2232", "2020c"));
        assertNotNull(admin.getOneEnrollment("s101163", "cosc3321", "2021a"));
        assertNull(admin.getOneEnrollment("s1011633", "cosc3321", "2021a"));
    }

    // Testing the getAllEnrollment method
    @org.junit.jupiter.api.Test
    void getAllEnrollments() {
        assertEquals(admin.getAllEnrollments().size(), 15);
    }
}