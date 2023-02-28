package projecttwo;

/**
 * This class represents a student that is enrolled,
 * and must be in the roster. These students have a profile
 * and a number of credits completed prior to being enrolled.
 * @author Aryan Jairath, Anis Chihoub
 */
public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;


    /**
     * Creates an enroll student object with a profile
     * field along with credits enrolled.
     * @param profile The profile of the enrolled student
     * @param creditsEnrolled The number of credits enrolled
     * that the student is enrolling with
     */
    public EnrollStudent(Profile profile, int creditsEnrolled) {
        this.profile = profile;
        this.creditsEnrolled = creditsEnrolled;
    }


    /**
     * Gives access to the Profile field of an enrolled student
     * @return A Profile object of a student that is in the enrolled
     * array
     */
    public Profile getProfile() {
        return profile;
    }


    /**
     * This method gives access to the credits enrolled
     * for a particular enrolled student
     * @return An integer specifying the creditsEnrolled
     * for a particular student that is enrolled.
     */
    public int getCreditsEnrolled() {
        return creditsEnrolled;
    }


    /**
     * This method compares two enrolled students by comparing
     * their respective profiles.
     * @param studentToCheck The student that is enrolled that
     * is being compared with
     * @return A boolean representing if studentToCheck is greater,
     * less than, or equal to the student it is compared with
     */
    @Override
    public boolean equals(Object studentToCheck) {
        if (studentToCheck instanceof EnrollStudent) {
            if (this.profile.equals(((EnrollStudent) studentToCheck).getProfile()))
                return true;
        }
        return false;
    }


    /**
     * This method makes a String representation
     * of the enroll student object
     * @return A String with containing the enrolled students
     * profile and credits enrolled.
     */
    @Override
    public String toString(){
        return this.profile.toString() + " " + this.creditsEnrolled;
    }
}
