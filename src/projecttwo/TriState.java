package projecttwo;

/**
 * This class represents a Tristate student that extends NonResident
 * and has fields such as profile, major, creditCompleted,
 * and state.
 * @author Aryan Jairath, Anis Chihoub
 */
public class TriState extends NonResident{
    private String state;

    private static final int TUITIONCOST = 29737;
    private static final int UNIFEE = 3268;
    private static final int INSURANCE = 2650;

    private static final int PARTTIME = 966;
    private static final Double PARTTIMEUNIFEE = 0.8;
    private static final int MINCREDFULLTIME = 12;
    private static final int MAXCREDFULLTIME = 16;
    private static final int SCHOLARSHIPNY = 4000;
    private static final int SCHOLARSHIPCT = 5000;
    private static final int INITIALTUITION = 0;


    /**
     * Defines a TriState student based on a profile, major
     * credit completed, and state.
     * @param profile A profile of a particular TriState student
     * @param major The major of the student
     * @param creditCompleted The credits completed by the student
     * @param state The state from which the student is from
     */
    public TriState(Profile profile, Major major,
                    int creditCompleted, String state) {
        super(profile, major, creditCompleted);
        this.state = state;
    }


    /**
     * Generates a string version of the TriState object.
     * @return a String containing the details of this TriState object.
     */
    @Override
    public String toString(){
        String outString = super.toString();
        return outString + "(tri-state: " + this.state + ")";
    }


    /**
     * Gives access to the state field for a Tristate
     * @return A string representing the state
     */
    public String getState(){
        return this.state;
    }


    /**
     * Calculates the tuition due for an Tristate object
     * @param creditsEnrolled The number of credits that the Tristate object
     * is enrolling with for the semester.
     * @return A double value representing the tuition due for a particular
     * Tristate student
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
        }else if(creditsEnrolled > MAXCREDFULLTIME){
            tuitionVal += TUITIONCOST;
            tuitionVal += UNIFEE;
            tuitionVal += PARTTIME * (creditsEnrolled - MAXCREDFULLTIME);
        }
        if(this.state.equalsIgnoreCase("NY"))
            tuitionVal -= SCHOLARSHIPNY;
        if(this.state.equalsIgnoreCase("CT"))
            tuitionVal -= SCHOLARSHIPCT;
        return tuitionVal;
    }
}
