package projecttwo;
import static org.junit.Assert.*;

/**
 * This class uses JUnit to test the isValid method for the Date
 * class using a variety of different test cases.
 * @author Aryan Jairath, Anis Chihoub
 */
public class DateTest {

    /**
     * Tests isValid with 29 days in February for a non leap
     * year
     */
    @org.junit.Test
    public void test_isValid_daysInFeb_nonLeap() {
        Date date = new Date("2/29/2003");
        assertFalse(date.isValid());
    }

    /**
     * Tests isValid with negative values in month, year,
     * and day
     */
    @org.junit.Test
    public void test_isValid_negativeFields() {
        Date date = new Date("-9/-2/-2023");
        assertFalse(date.isValid());
    }

    /**
     * Tests isValid with 31 days in May which only
     * has 30 days
     */
    @org.junit.Test
    public void test_isValid_MaxThirtyDays() {
        Date date = new Date("4/31/2003");
        assertFalse(date.isValid());
    }

    /**
     * Tests isValid with a month value that is greater
     * than twelve
     */
    @org.junit.Test
    public void test_isValid_more_Than_TwelveMonths() {
        Date date = new Date("13/7/2003");
        assertFalse(date.isValid());
    }

    /**
     * Tests isValid with more than 31 days in December
     * which only has 31 days
     */
    @org.junit.Test
    public void test_isValid_MaxThirtyOneDays() {
        Date date = new Date("12/32/2003");
        assertFalse(date.isValid());
    }

    /**
     * Tests isValid with 31 days in months that actually
     * have 31 days
     */
    @org.junit.Test
    public void test_isValid_ThirtyOneDayMonth() {
        Date date = new Date("1/31/2003");
        assertTrue(date.isValid());
    }

    /**
     * Tests isValid with 29 days in February for a leap
     * year
     */
    @org.junit.Test
    public void test_isValid_daysInFeb_Leap() {
        Date date = new Date("2/29/2004");
        assertTrue(date.isValid());
    }
}