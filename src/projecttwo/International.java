package projecttwo;

/**
 * This class represents a International student that extends Student
 * and has fields such as profile, major, creditCompleted,
 * and isStudyAbroad.
 * @author Aryan Jairath, Anis Chihoub
 */
public class International extends Student {

    private boolean isStudyAbroad;

    private static final int EQUAL = 0;
    private static final int GREATER = 1;
    private static final int LESS = -1;

    private static final int TUITIONCOST = 29737;
    private static final int UNIFEE = 3268;
    private static final int INSURANCE = 2650;

    private static final int PARTTIME = 966;
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
     * @param isStudyAbroad A boolean representing if the student studies abroad
     */
    public International(Profile profile, Major major,
                         int creditCompleted, boolean isStudyAbroad) {
        super(profile, major, creditCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }


    /**
     * Calculates the tuition due for an International object
     * @param creditsEnrolled The number of credits that the International object
     * is enrolling with for the semester.
     * @return A double value representing the tuition due for a particular
     * International student
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        double tuitionVal = INITIALTUITION;
        if(this.isStudyAbroad && creditsEnrolled > MINCREDFULLTIME)
            return tuitionVal;

        tuitionVal += UNIFEE;
        tuitionVal += INSURANCE;
        if(this.isStudyAbroad)
            return tuitionVal;
        tuitionVal += TUITIONCOST;
        if(creditsEnrolled > MAXCREDFULLTIME)
            tuitionVal += PARTTIME * (creditsEnrolled - MAXCREDFULLTIME);
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


    /**
     * Sets the study abroad status of an internatinal student.
     * @param isStudyAbroad a boolean representing if the student is study abroad
     */
    public void setStudyAbroad(boolean isStudyAbroad){
        this.isStudyAbroad = isStudyAbroad;
    }


    /**
     * Gets the number of credits for an international student.
     * @return an integer representing the number of credits.
     */
    public int getCredits(){
        return this.getCredits();
    }


    /**
     * returns the status of an international student being a study abroad.
     * @return a boolean, indicating if this student is a study abroad student.
     */
    public boolean isStudyAbroad(){
        return this.isStudyAbroad;
    }


    /**
     * Gives the status for a particular
     * International student and if it is study abroad
     * @return A String representing if the student is a typical international
     * student or a study abroad international one
     */
    @Override
    public String toString(){
      String outString = super.toString() + "(non-resident)";
      if(isStudyAbroad()){
          outString += "(international:study abroad)";
      }else{
          outString += "(international)";
      }

      return outString;
    }
}
