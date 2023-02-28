package projecttwo;
import static org.junit.Assert.*;

/**
 * This class uses JUnit to test the add method for the
 * Roster class using a variety of different test cases.
 * @author Aryan Jairath, Anis Chihoub
 */
public class RosterTest {

    /**
     * Tests roster add method with an International student
     * where the method returns true
     */
    @org.junit.Test
    public void test_RosterAddWithInternationalTrue() {
        Date date = new Date("1/1/2002");
        String Fname = "Lily";
        String Lname = "Chang";
        Profile studentProfile = new Profile(Lname,Fname, date);
        int creditsCompleted = 55;
        int creditsEnrolled = 12;
        boolean isStudyAbroad = false;
        International international = new International(studentProfile, Major.BAIT,
                creditsCompleted, isStudyAbroad);
        Roster testRoster = new Roster();
        assertTrue(testRoster.add(international));
    }

    /**
     * Tests roster add method with an International student
     * where the method returns false
     */
    @org.junit.Test
    public void test_RosterAddWithInternationalFalse() {
        Date date = new Date("1/1/2002");
        String Fname = "Lily";
        String Lname = "Chang";
        Profile studentProfile = new Profile(Lname, Fname, date);
        int creditsCompleted = 55;
        int creditsEnrolled = 12;
        boolean isStudyAbroad = false;
        International international = new International(studentProfile, Major.BAIT,
                creditsCompleted, isStudyAbroad);
        Roster testRoster = new Roster();
        testRoster.add(international);
        assertFalse(testRoster.add(international));
    }

    /**
     * Tests roster add method with a tri-state student
     * where the method returns true
     */
    @org.junit.Test
    public void test_RosterAddWithTristateTrue() {
        Date date = new Date("1/1/2002");
        String Fname = "Lily";
        String Lname = "Chang";
        Profile studentProfile = new Profile(Lname, Fname, date);
        int creditsCompleted = 55;
        int creditsEnrolled = 12;
        String state = "NY";
        TriState triStateStudent = new TriState(studentProfile, Major.BAIT,
                creditsCompleted, state);
        Roster testRoster = new Roster();
        assertTrue(testRoster.add(triStateStudent));
    }

    /**
     * Tests roster add method with a tri-state student
     * where the method returns false
     */
    @org.junit.Test
    public void test_RosterAddWithTristateFalse() {
        Date date = new Date("1/1/2002");
        String Fname = "Lily";
        String Lname = "Chang";
        Profile studentProfile = new Profile(Lname, Fname, date);
        int creditsCompleted = 55;
        int creditsEnrolled = 12;
        String state = "NY";
        TriState triStateStudent = new TriState(studentProfile, Major.BAIT,
                creditsCompleted, state);
        Roster testRoster = new Roster();
        testRoster.add(triStateStudent);
        assertFalse(testRoster.add(triStateStudent));
    }

    /**
     * Tests roster remove method with a Tristate student
     * where the method returns false
     */
    @org.junit.Test
    public void test_RosterRemoveWithTristateFalse() {
        Date date = new Date("1/1/2002");
        String Fname = "Lily";
        String Lname = "Chang";
        Profile studentProfile = new Profile(Lname, Fname, date);
        int creditsCompleted = 55;
        int creditsEnrolled = 12;
        String state = "NY";
        TriState triStateStudent = new TriState(studentProfile, Major.BAIT,
                creditsCompleted, state);
        Roster testRoster = new Roster();
        assertFalse(testRoster.remove(triStateStudent));
    }

    /**
     * Tests roster remove method with a Tristate student
     * where the method returns True
     */
    @org.junit.Test
    public void test_RosterRemoveWithTristateTrue() {
        Date date = new Date("1/1/2002");
        String Fname = "Lily";
        String Lname = "Chang";
        Profile studentProfile = new Profile(Lname, Fname, date);
        int creditsCompleted = 55;
        int creditsEnrolled = 12;
        String state = "NY";
        TriState triStateStudent = new TriState(studentProfile, Major.BAIT,
                creditsCompleted, state);
        Roster testRoster = new Roster();
        testRoster.add(triStateStudent);
        assertTrue(testRoster.remove(triStateStudent));
    }

    /**
     * Tests roster remove method with an International student
     * where the method returns false
     */
    @org.junit.Test
    public void test_RosterRemoveWithInternationalFalse() {
        Date date = new Date("1/1/2002");
        String Fname = "Lily";
        String Lname = "Chang";
        Profile studentProfile = new Profile(Lname,Fname, date);
        int creditsCompleted = 55;
        int creditsEnrolled = 12;
        boolean isStudyAbroad = false;
        International international = new International(studentProfile, Major.BAIT,
                creditsCompleted, isStudyAbroad);
        Roster testRoster = new Roster();
        assertFalse(testRoster.remove(international));
    }

    /**
     * Tests roster remove method with an International student
     * where the method returns true
     */
    @org.junit.Test
    public void test_RosterRemoveWithInternationalTrue() {
        Date date = new Date("1/1/2002");
        String Fname = "Lily";
        String Lname = "Chang";
        Profile studentProfile = new Profile(Lname, Fname, date);
        int creditsCompleted = 55;
        int creditsEnrolled = 12;
        boolean isStudyAbroad = false;
        International international = new International(studentProfile, Major.BAIT,
                creditsCompleted, isStudyAbroad);
        Roster testRoster = new Roster();
        testRoster.add(international);
        assertTrue(testRoster.remove(international));
    }


}