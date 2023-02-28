package projecttwo;

/**
 * This is an abstract class that represents a student.
 * Each student has a profile, major, and number of
 * credits completed. All students also have some amount of
 * tuition due.
 *  @author Aryan Jairath, Anis Chihoub
 */
public abstract class Student implements Comparable<Student> {
    private Profile profile;
    private Major major; //Major is an enum type
    private int creditCompleted;

    public static final int LESSTHAN = -1;
    public static final int EQUALTO = 0;
    public static final int GREATERTHAN = 1;

    public static final int ZEROCREDITS = 0;

    public static final int FRESHMANCREDITS = 29;

    public static final int SOPHOMORECREDITS = 30;

    private static final int JUNIORCREDITS = 60;

    public static final int SENIORRCREDITS = 90;

    /**
     * Abstract method that finds tuition due for particular type of student
     * @param creditsEnrolled Number of credits student is enrolled with
     * @return A double representing the tuition due
     */
    public abstract double tuitionDue(int creditsEnrolled);

    /**
     * Abstract method that says if a particular student is a resident
     * @return A boolean representing the residential status
     */
    public abstract boolean isResident();

    /**
     * Constructor for the Student class
     * Defines the profile, major, and
     * creditsCompleted for the student.
     * @param profile A profile representing the name,lastname, and dob of the student
     * @param major An enum type representing the major of the student
     * @param creditCompleted An integer representing the number of credits completed by the student
     */
    public Student(Profile profile, Major major, int creditCompleted){
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }


    /**
     * This method returns the profile of a particular student
     * @return A Profile object of a particular student
     */
    public Profile getProfile(){
        return this.profile;
    }


    /**
     * This method returns the standing of a student
     * based on the number of credits he or she has.
     * @return A string representing the standing of a
     * student.
     */
    public String collegeYear(){
        if(this.creditCompleted <= FRESHMANCREDITS){
            return "(Freshman)";
        }else if(this.creditCompleted >= SOPHOMORECREDITS &&
                this.creditCompleted < JUNIORCREDITS){
            return "(Sophomore)";
        }
        else if(this.creditCompleted >= JUNIORCREDITS &&
                this.creditCompleted < SENIORRCREDITS){
            return "(Junior)";
        }
        else {
            return "(Senior)";
        }
    }


    /**
     * This method returns the major of a particular student
     * @return A Major object of a particular student
     */
    public Major getMajor(){
        return this.major;
    }

    /**
     * Checks if a Student is valid based on the credits they
     * are enrolled with
     * @param creditEnrolled The number of credits they are enrolled with
     * @return A boolean representing if they are valid or not
     */
    public boolean isValid(int creditEnrolled){
        if(creditEnrolled >= ZEROCREDITS)
            return true;
        return false;
    }

    /**
     * Sets the major of a student to a new major when changing
     * @param newMajor The major being associated with the student
     */
    public void setMajor(Major newMajor){
        this.major = newMajor;
    }

    /**
     * This method returns the number of credits completed by a student
     * @return An int representing the number of credits completed associated with a student
     */
    public int getCreditCompleted(){
        return this.creditCompleted;
    }

    /**
     * This method provides a string representation of a student
     * @return A string containing the attributes of the Student object
     */
    @Override
    public String toString(){
        return this.profile.toString() + " (" + this.major.getCode()
                + " " + this.major + " " +
                this.major.getSchool() +") credits completed: "
                + this.creditCompleted + " " + collegeYear();
    }

    /**
     * Compares two Student objects
     * @param compareStudent the object to be compared.
     * @return int, an integer representing if one Student
     * is greater than the other in the set (-1,1).
     */
    @Override
    public int compareTo(Student compareStudent) {
        if(compareStudent == null){
            return GREATERTHAN;
        }
        if(this.profile.compareTo(compareStudent.profile) > EQUALTO){
            return GREATERTHAN;
        }
        else if(this.profile.compareTo(compareStudent.profile) < EQUALTO)
            return LESSTHAN;
        else
            return EQUALTO;
    }
    /**
     * This method determines if two student objects are equal
     * @param objToCheck The object that we are comparing
     * @return a boolean, true if they are equal, false otherwise
     */
    @Override
    public boolean equals(Object objToCheck){
        if(objToCheck instanceof Student){
            return ((Student) objToCheck).profile.equals(this.profile);
        }
        return false;
    }

    /**
     * updates the credit completed parameter in the student object
     * @param creditCompleted an integer which has the credits completed
     */
    public void setCreditCompleted(int creditCompleted){
        this.creditCompleted = creditCompleted;
    }
}
