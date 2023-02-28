package projecttwo;

/**
 * This class represents the students that are
 * currently enrolled. Students are enrolled into an
 * array that contains EnrollStudent objects
 * @author Aryan Jairath, Anis Chihoub
 */
public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;
    public static final int INITIALSIZE = 1;
    public static final int ARRAYINDEXMIUSONE = 1;

    public static final int INDEXTOSUBTRACT = 2;
    /**
     * Initializes a Roster object, initializes the roster array,
     * and sets the initial size to one.
     */
    public Enrollment(){
        this.size = INITIALSIZE;
        enrollStudents = new EnrollStudent[this.size];
    }


    /**
     * Adds a student to the enrollment array if
     * student is not already in the array
     * @param enrollStudent The student to be added to the array
     */
    public void add(EnrollStudent enrollStudent) {
        if(contains(enrollStudent))
            return;
        for(int i = 0; i < enrollStudents.length; i++){
            if(enrollStudents[i] == null){
                enrollStudents[i] = enrollStudent;
            }
        }
        size++;
        EnrollStudent[] largerStudentRoster =
                new EnrollStudent[enrollStudents.length + ARRAYINDEXMIUSONE];
        for(int i = 0; i < largerStudentRoster.length - ARRAYINDEXMIUSONE; i++){
            largerStudentRoster[i] = enrollStudents[i];
        }
        this.enrollStudents = largerStudentRoster;
    } //add to the end of array


    /**
     * Removes a specified student from the enrollment array
     * @param enrollStudent The student to be removed from the enrollment.
     */
    public void remove(EnrollStudent enrollStudent) {
        if(!(contains(enrollStudent)))
            return;
        for(int i = 0; i < enrollStudents.length; i++){
            if(enrollStudents[i] == null)
                continue;
            if(enrollStudents[i].equals(enrollStudent)) {
                EnrollStudent tempStudent = enrollStudent;
                enrollStudents[i] = enrollStudents[enrollStudents.length - INDEXTOSUBTRACT];
                enrollStudents[enrollStudents.length - INDEXTOSUBTRACT] = tempStudent;
                size--;
                break;
            }
        }
    }


    /**
     * This method checks if the array contains a certain student.
     * @param enrollStudent The student being checked for presence in array.
     * @return A boolean representing if the student is in array.
     */
    public boolean contains(EnrollStudent enrollStudent) {
        for(int i = 0; i < enrollStudents.length; i++){
            if(enrollStudents[i] == null)
                return false;
            if(enrollStudents[i].equals(enrollStudent)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns the object corresponding to a student
     * @param studentDesired an Enroll Student object we are looking for
     * @return an EnrollStudent object that contains the data we want.
     */
    public EnrollStudent findStudent(EnrollStudent studentDesired){
        for(int i = 0; i < enrollStudents.length; i++){
            if(enrollStudents[i].equals(studentDesired)){
                return enrollStudents[i];
            }
        }
        return null;
    }


    /**
     * This method returns the enrollment array.
     * @return A EnrollStudent array representing the enrollment
     * of all students.
     */
    public EnrollStudent[] getEnrollment(){
        return this.enrollStudents;
    }

    /**
     * Print the enrollment array sorted in the order
     * that they were added to the array
     */
    public void print() {
        for(int i = 0; i < enrollStudents.length; i++){
            if(enrollStudents[i] != null)
                System.out.println(enrollStudents[i].toString());
        }
    }


    /**
     * Gets the size of the enrolled student array
     * @return an integer indicating the size of the enrollment array.
     */
    public int getSize(){
        return this.enrollStudents.length;
    }


    /**
     * Checks if enrolled students array is empty.
     * @return a boolean indicating if the array is empty or not.
     */
    public boolean isEmpty(){
        boolean allNull = true;
        for(int i = 0; i < this.enrollStudents.length; i++){
            if(this.enrollStudents[i] != null){
                allNull = false;
            }
        }
        return allNull;
    }
}
