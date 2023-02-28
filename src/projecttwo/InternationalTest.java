package projecttwo;
import static org.junit.Assert.*;

/**
 * This class uses JUnit to test the tuitionDue method for the
 * International class using a variety of different test cases.
 * @author Aryan Jairath, Anis Chihoub
 */
public class InternationalTest {

    /**
     * Tests tuitionDue with an international student that
     * is not a study abroad student
     */
    @org.junit.Test
    public void test_tuitionDue_studyAbroadisTrue() {
        Date date = new Date("1/1/2002");
        String Fname = "Lily";
        String Lname = "Chang";
        Profile studentProfile = new Profile(Lname, Fname, date);
        int creditsCompleted = 55;
        int creditsEnrolled = 12;
        boolean isStudyAbroad = false;
        International international = new International(studentProfile,
                Major.BAIT, creditsCompleted, isStudyAbroad);
        double internationalTuition = international.tuitionDue(creditsEnrolled);
        double expectedInternationalTuition = 35655.0;
        double deltaExpectation = .0001;
        assertEquals(internationalTuition, expectedInternationalTuition,
                deltaExpectation);
    }


    /**
     * Tests tuitionDue with an international student that
     * is a study abroad student.
     */
    @org.junit.Test
    public void test_tuitionDue_studyAbroadIsFalse() {
        Date date = new Date("1/1/2002");
        String Fname = "Lily";
        String Lname = "Chang";
        Profile studentProfile = new Profile(Lname, Fname, date);
        int creditsCompleted = 55;
        int creditsEnrolled = 12;
        boolean isStudyAbroad = true;
        International international = new International(studentProfile, Major.BAIT,
                creditsCompleted, isStudyAbroad);
        double internationalTuition = international.tuitionDue(creditsEnrolled);
        double expectedInternationalTuition = 5918;
        double deltaExpectation = .0001;
        assertEquals(internationalTuition, expectedInternationalTuition,
                deltaExpectation);
    }
}


