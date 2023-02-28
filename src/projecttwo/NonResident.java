package projecttwo;

/**
 * This class represents a NonResident that extends Student
 * and has fields such as profile, major, and credit
 * completed.
 * @author Aryan Jairath, Anis Chihoub
 */
public class NonResident extends Student{

    private static final int EQUAL = 0;
    private static final int GREATER = 1;
    private static final int LESS = -1;
    private static final int TUITIONCOST = 29737;
    private static final int UNIFEE = 3268;
    private static final int PARTTIME = 966;
    private static final Double PARTTIMEUNIFEE = 0.8;
    private static final int MINCREDFULLTIME = 12;
    private static final int MAXCREDFULLTIME = 16;
    private static final int INITIALTUITION = 0;


    /**
     * Constructor for the Student class
     * Defines the profile, major, and
     * creditsCompleted for the student.
     * @param profile A profile representing the name,lastname, and dob of the student
     * @param major An enum type representing the major of the student
     * @param creditCompleted An integer representing the number of credits completed by the student
     */
    public NonResident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
    }

    /**
     * Calculates the tuition due for a NonResident object
     * @param creditsEnrolled The number of credits that the NonResident object
     * is enrolling with for the semester.
     * @return A double value representing the tuition due for a particular
     * NonResident student
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
            return tuitionVal;
        }else if(creditsEnrolled > MAXCREDFULLTIME){
            tuitionVal += TUITIONCOST;
            tuitionVal += UNIFEE;
            tuitionVal += PARTTIME * (creditsEnrolled - MAXCREDFULLTIME);
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
        return false;
    }

    /**
     * Generates A string version of the NonResident object.
     * @return A string containing the details of the NonResident object.
     */
    @Override
    public String toString(){
        String outString = super.toString();
        if(isResident()){
            outString += "(Resident)";
        }else{
            outString += "(non-resident)";
        }
        return outString;
    }

    /**
     * This method compares two Resident objects
     * @param student The object to be compared.
     * @return 1 if student is less than the Resident being compared to, 0 if they are equal,
     * and -1 if student is greater than the Resident Object.
     */
    @Override
    public int compareTo(Student student) {
        if(this.getProfile().compareTo(student.getProfile()) == GREATER)
            return GREATER;
        else if(this.getProfile().compareTo(student.getProfile()) == EQUAL)
            return EQUAL;
        return LESS;

    }

}
