package projecttwo;

/**
 * This class represents a Resident that extends Student
 * and has fields profile, major, credit
 * completed, and scholarship.
 * @author Aryan Jairath, Anis Chihoub
 */
public class Resident extends Student {
    private int scholarship;

    private static final int EQUAL = 0;
    private static final int GREATER = 1;
    private static final int LESS = -1;
    private static final int TUITIONCOST = 12536;
    private static final int UNIFEE = 3268;
    private static final int PARTTIME = 404;
    private static final Double PARTTIMEUNIFEE = 0.8;
    private static final int MINCREDFULLTIME = 12;
    private static final int MAXCREDFULLTIME = 16;
    private static final int INITIALTUITION = 0;


    /**
     * Constructor for the Student class
     * Defines the profile, major, and
     * creditsCompleted for the student.
     * @param profile A profile representing the name, lastname, and dob of student
     * @param major An enum type representing the major of the student
     * @param creditCompleted An integer representing the number of credits completed
     * @param scholarship An integer representing the scholarship the student received
     */
    public Resident(Profile profile, Major major,
                    int creditCompleted, int scholarship) {
        super(profile, major, creditCompleted);
        this.scholarship = scholarship;
    }

    /**
     * Calculates the tuition due for a resident object
     * @param creditsEnrolled The number of credits that the Resident object
     * is enrolling with for the semester.
     * @return A double value representing the tuition due for a particular student
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        double tuitionVal = INITIALTUITION;
        if(creditsEnrolled < MINCREDFULLTIME){
            tuitionVal += PARTTIME * creditsEnrolled;
            tuitionVal += PARTTIMEUNIFEE * UNIFEE;
            return tuitionVal;
        }else if(creditsEnrolled >= MINCREDFULLTIME
                && creditsEnrolled <= MAXCREDFULLTIME){
            tuitionVal += TUITIONCOST;
            tuitionVal += UNIFEE;
            tuitionVal -= this.scholarship;
            return tuitionVal;
        }else if(creditsEnrolled > MAXCREDFULLTIME){
            tuitionVal += TUITIONCOST;
            tuitionVal += UNIFEE;
            tuitionVal += PARTTIME * (creditsEnrolled - MAXCREDFULLTIME);
            tuitionVal -= this.scholarship;
            return tuitionVal;
        }
        return tuitionVal;
    }

    /**
     * This method determines if a Resident object is a Resident
     * @return A boolean representing if the object is a Resident
     */
    @Override
    public boolean isResident() {
        return true;
    }

    /**
     * This method gives access to the scholarship field
     * @return An integer representing the amount a particular Resident
     * has in scholarship
     */
    public int getScholarship(){
        return this.scholarship;
    }

    /**
     * Setter method for scholarship class
     * @param scholarship, an integer containing a scholarship amount
     */
    public void setScholarship(int scholarship){
        this.scholarship = scholarship;
    }

    /**
     * This method compares two Resident objects
     * @param student The object to be compared.
     * @return 1 if student is less than the Resident being compared to, 0 if they are equal,
     * and -1 if stuednt is greater than the Resident Object.
     */
    @Override
    public int compareTo(Student student) {
        if(this.getProfile().compareTo(student.getProfile()) == GREATER)
            return GREATER;
        else if(this.getProfile().compareTo(student.getProfile()) == EQUAL)
            return EQUAL;
        return LESS;

    }

    /**
     * Generates a string version of the Resident object.
     * @return a String containing the details of this Resident object.
     */
    @Override
    public String toString(){
        return super.toString() + "(resident)";
    }
}
