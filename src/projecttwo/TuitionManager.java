package projecttwo;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * This class reads input from command line and
 * takes a series of different options such as adding a student,
 * removing a student, changing a major, and printing students
 * in different ways.
 * @author Anis Chihoub, Aryan Jairath
 */
public class TuitionManager {

    private static final int INTERNATIONALSTUDYABROADCREDITS = 13;

    private static final int SCHOLARSHIPAMOUNT = 10000;
    private static final int MAXLENGTHOFS = 5;

    private static final int MAXLENGTHOFSCOMMAND = 4;

    private static final int ZEROSCHOLARSHIP = 0;
    private static final int FRACTIONDIGITS = 2;
    private static final int SAMEPROFILE = 0;
    private static final int EMPTYCOMMAND  = 0;
    private static final int MAXLENGTHOFAI = 7;
    private static final int ABROADINDEX = 6;

    private static final int RESIDENTSCHOLARSHIPAMOUNT = 10000;

    private static final int FIRSTINDEX = 0;
    private static final int SECONDINDEX = 1;
    private static final int TWOCHARLENGTH = 2;
    private static final int MINCREDITS = 3;
    private static final int MAXCREDITS = 24;

    private static final int NOSTRINGLENGTH = 0;
    private static final int TYPEOFSTUDENT = 0;

    private static final int CREDITINDEX = 5;
    private static final int CREDITINDEXENROLLED = 4;

    private static final int MAJORINDEX = 4;
    private static final int STATEINDEX = 6;

    private static final int PRICE = 4;

    private static final int LENGTHOFLS = 2;


    private static final int DOBINDEX = 3;
    private static final int LNAMEINDEX = 2;
    private static final int FNAMEINDEX = 1;
    private static final int MINIMUMAGE = 16;
    private static final int PARTTIME = 12;

    private static final int SAMEDATE = 0;
    private static final int ZEROCREDITS = 0;
    private static final int NEGATIVEMULTIPLIER = -1;
    private static final int MISSINGDATA = 4;
    private static final int MISSINGCODE = 6;
    private static final int SEARGUMENTS = 1 ;
    private static final int GRADCREDITS = 120;


    private Roster studentRoster = new Roster();
    private Enrollment studentEnrollment = new Enrollment();


    /**
     * This method checks is the command is a one letter command from the
     * valid options except P, which is checked for in the following method.
     * @param command a string representing a command
     * @return a boolean, true if the command is valid. False otherwise
     */
    private boolean isOneChar(String command){
        return (command.charAt(FIRSTINDEX) == 'E' || command.charAt(FIRSTINDEX) == 'D'
                || command.charAt(FIRSTINDEX) == 'S' || command.charAt(FIRSTINDEX) == 'R'
                || command.charAt(FIRSTINDEX) == 'Q' || command.charAt(FIRSTINDEX) == 'C'
                || command.charAt(FIRSTINDEX) == 'P');
    }


    /**
     * This method checks is the command is a two letter command.
     * Since two letter commands can start with a P, we check for
     * the P command as well as PS and PC.
     * @param command a string representing a command
     * @return a boolean, true if the command is valid. False otherwise
     */
    private boolean isTwoChars(String command){
        if(command.length() < TWOCHARLENGTH){
            return false;
        }
        if (isOneChar(command)){
            return true;
        }else{
            String firstTwoChars = command.substring(FIRSTINDEX,TWOCHARLENGTH);
            if(firstTwoChars.equals("AR") || firstTwoChars.equals("AN")
                    || firstTwoChars.equals("AT") || firstTwoChars.equals("AI")
                    || firstTwoChars.equals("PE") || firstTwoChars.equals("PT")
                    || firstTwoChars.equals("LS") ||
            firstTwoChars.equals("PS") || firstTwoChars.equals("PC")){
                return true;
            }else{
                return false;
            }
        }
    }


    /**
     * This method receives a string containing a command and checks
     * if it is valid based on the ruleset provided in the writeup.
     * @param command: a string containing a command read in from the scanner
     * class.
     * @return a boolean, indicating if the command is valid or not.
     */
    private boolean checkInvalid(String command){
        if(command.length() > NOSTRINGLENGTH && (isOneChar(command)
                || isTwoChars(command))){
            return false;
        }else{
            return true;
        }
    }


    /**
     * This method checks the date n years ago from today
     * @param nYears An integer representing the number of years
     * ago from today to calculate the date
     * @return A date object representing the date n years ago
     */
    private Date wasNYearsAgo(int nYears){
        Calendar nYearsAgo = Calendar.getInstance();
        nYearsAgo.add(Calendar.YEAR, NEGATIVEMULTIPLIER * nYears);
        int year = nYearsAgo.get(Calendar.YEAR);
        int month = nYearsAgo.get(Calendar.MONTH)+SECONDINDEX;
        int day = nYearsAgo.get(Calendar.DATE);
        Date dateNYearsAgo = new Date(month + "/" + day + "/" + year);
        return dateNYearsAgo;
    }


    /**
     * This method validates if the input number is an integer
     * @param checkCredit a String representing an integer to be checked
     * @return a boolean value, true if the value is an integer, otherwise false
     */
    public static boolean isInteger(String checkCredit) {
        try {
            Integer.parseInt(checkCredit);
        } catch(NumberFormatException exception) {
            return false;
        } catch(NullPointerException exception){
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    /**
     * This method checks if an input string qualifies as a valid major based on enum class
     * @param testMajor A string that is being checked for whether it is a major or not
     * @return A boolean value returning true if the string parameter is a valid major
     */
    public static boolean isValidMajor(String testMajor) {
        try {
            Major studentMajor = Major.valueOf(testMajor.toUpperCase());
            return true;
        } catch (IllegalArgumentException exception) {
            System.out.println("Major code invalid:" + testMajor);
            return false;
        }
    }


    /**
     * This method checks if an input string is a valid school
     * @param testSchool A string that is being checked for
     * whether it is an existing school or not
     * @return A boolean value returning true if the string parameter is a valid school
     */
    public static boolean isValidSchool(String testSchool) {
        for (Major day : Major.values()) {
            if(day.getSchool().equals(testSchool.toUpperCase()))
                return true;
        }
        return false;
    }


    /**
     * Checks a student's DOB, credits, and compares age from 16 years
     * @param parsedCommand an Array of Strings where each index
     * contains student information as an individual String
     * @return A boolean representing if the student is valid for roster
     */
    private boolean initialStudentChecks(String[] parsedCommand){
        int numberOfYears = MINIMUMAGE;
        Date dob = new Date(parsedCommand[DOBINDEX]);
        Date sixteenYearsAgo = wasNYearsAgo(numberOfYears);
        if(!dob.isValid()){
            System.out.println("DOB is invalid: " + dob.toString() +
                    " not a valid calendar date!");
            return false;
        }
        if(!isInteger(parsedCommand[CREDITINDEX])){
            System.out.println("Credits completed invalid: not an integer!");
            return false;
        }
        if(Integer.parseInt(parsedCommand[CREDITINDEX]) < ZEROCREDITS){
            System.out.println("Credits completed invalid: cannot be negative!");
            return false;
        }
        if(dob.compareTo(sixteenYearsAgo) > ZEROCREDITS){
            System.out.println("DOB is invalid: " + dob.toString() +
                    " is younger than 16 years old!");
            return false;
        }
        return true;
    }

    /**
     * Checks a student's DOB, credits, and compares age from 16 years
     * @param parsedCommand an Array of Strings where each index
     * contains student information as an individual String
     * @return A boolean representing if the student is valid for enrollment
     */
    private boolean initialEnrollStudentChecks(String[] parsedCommand){
        int numberOfYears = MINIMUMAGE;
        Date dob = new Date(parsedCommand[DOBINDEX]);
        Date sixteenYearsAgo = wasNYearsAgo(numberOfYears);
        Profile profile = new Profile(parsedCommand[LNAMEINDEX],
                parsedCommand[FNAMEINDEX], dob);
        Student[] roster = studentRoster.getRoster();
        String type = "";
        for(int i = 0; i < roster.length; i++){
            if(roster[i] != null) {
                if (roster[i].getProfile().equals(profile)
                        && roster[i].isResident())
                    type = typeOfStudent(roster[i]);
                else if (roster[i].getProfile().equals(profile)
                        && roster[i] instanceof NonResident)
                    type = typeOfStudent(roster[i]);
                else if (roster[i].getProfile().equals(profile)
                        && roster[i] instanceof
                        International && ((International) roster[i]).isStudyAbroad())
                    type = typeOfStudent(roster[i]);
                else if (roster[i].getProfile().equals(profile)
                        && roster[i] instanceof International)
                    type = typeOfStudent(roster[i]);
            }
        }
        return checkArguments(parsedCommand, dob, sixteenYearsAgo, type);
    }


    /**
     * Checks if an enrolled students' arguments are valid
     * @param parsedCommand an Array of Strings where each index
     * contains student information as an individual String
     * @param dob The date of birth for the student to be enrolled
     * @param sixteenYearsAgo The date sixteen years ago from today
     * @param type The type of student: international, resident
     * non-resident
     * @return A boolean representing if the arguments are valid or not
     */
    private boolean checkArguments(String[] parsedCommand, Date dob,
                                   Date sixteenYearsAgo, String type){
        if(parsedCommand.length != MAXLENGTHOFS){
            System.out.println("Missing data in command line.");
            return false;
        }
        if(!dob.isValid()){
            System.out.println("DOB is invalid: " + dob.toString() +
                    " not a valid calendar date!");
            return false;
        }
        if(!isInteger(parsedCommand[CREDITINDEXENROLLED])){
            System.out.println("Credits enrolled is not an integer!");
            return false;
        }
        if(!(Integer.parseInt(parsedCommand[CREDITINDEXENROLLED]) >= MINCREDITS
                && Integer.parseInt(parsedCommand[CREDITINDEXENROLLED]) <= MAXCREDITS)) {
            System.out.println(type + " " + Integer.parseInt(
                            parsedCommand[CREDITINDEXENROLLED]) + ": invalid credit hours.");
            return false;
        }
        if(type.equals("(International studentstudy abroad)") &&
                Integer.parseInt(parsedCommand[CREDITINDEXENROLLED]) > PARTTIME ){
            System.out.println(type + " " + Integer.parseInt(
                    parsedCommand[CREDITINDEXENROLLED]) + ": invalid credit hours.");
            return false;
        }
        if(type.equals("(International student)") &&
                Integer.parseInt(parsedCommand[CREDITINDEXENROLLED]) < PARTTIME ){
            System.out.println(type + " " + Integer.parseInt(
                    parsedCommand[CREDITINDEXENROLLED])+ ": invalid credit hours.");
            return false;
        }
        if(dob.compareTo(sixteenYearsAgo) > SAMEDATE){
            System.out.println("DOB is invalid: " + dob.toString() +
                    " is younger than 16 years old!");
            return false;
        }
        return true;
    }


    /**
     * Makes a Student object. Checks for invalid conditions and
     * prints out if there is an issue.
     * @param parsedCommand an Array of Strings where each index
     * contains student information as an individual String
     * @param checks a boolean representing if the student meets
     * qualified requirements to be added
     * @return an object of type Student after making it based on the parsed input
     * and checking if it can be a valid student for the roster
     */
    private Student makeStudent(String[] parsedCommand, boolean checks){
        if(checks && !initialStudentChecks(parsedCommand)){
                return null;
        }
        Date dob = new Date(parsedCommand[DOBINDEX]);
        String type = parsedCommand[TYPEOFSTUDENT];
        Profile profile =
                new Profile(parsedCommand[LNAMEINDEX],parsedCommand[FNAMEINDEX],dob);
        Major studentMajor;
        if(checks) {
            if (isValidMajor(parsedCommand[MAJORINDEX])) {
                studentMajor = Major.valueOf(parsedCommand[MAJORINDEX].toUpperCase());
            } else {
                return null;
            }
            int creditsCompleted = Integer.parseInt(parsedCommand[CREDITINDEX]);
            if(type.equals("AR") || type.equals("R"))
                return new Resident(profile, studentMajor, creditsCompleted, ZEROSCHOLARSHIP);
            else if(type.equals("AN") || type.equals("N"))
                return new NonResident(profile, studentMajor, creditsCompleted);
            else if(type.equals("AT") || type.equals("T")) {
                String state = parsedCommand[STATEINDEX];
                if(state.toUpperCase().equals("NY") || state.toUpperCase().equals("CT"))
                    return new TriState(profile, studentMajor,
                            creditsCompleted, state.toUpperCase());
            else{
                    System.out.println(state + ": Invalid State Code.");
                    return null;
                }
            }else if(type.equals("AI") || type.equals("I")) {
                if (parsedCommand.length == MAXLENGTHOFAI) {
                    if (parsedCommand[ABROADINDEX].equals("true"))
                        return new International(profile, studentMajor, creditsCompleted, true);
                    else
                        return new International(profile, studentMajor, creditsCompleted, false);
                } else
                    return new International(profile, studentMajor, creditsCompleted, false);
            }
        }
            return new Resident(profile, null, ZEROCREDITS, ZEROSCHOLARSHIP);
    }


    /**
     * Makes a EnrolledStudent object. Checks for invalid conditions and
     * prints out if there is an issue.
     * @param parsedCommand an array of strings where each index
     * contains enrolled student information as an individual string
     * @return an object of type EnrolledStudent after making it based on the parsed input
     * @param checks a boolean representing if a student passed initial checks
     * and checking if it can be a valid student for the Enrollment
     */
    private EnrollStudent makeEnrolledStudent(String[] parsedCommand, boolean checks) {
        if(checks){
            if(!initialEnrollStudentChecks(parsedCommand))
                return null;
        }
        Date dob = new Date(parsedCommand[DOBINDEX]);
        Profile profile =
                new Profile(parsedCommand[LNAMEINDEX],parsedCommand[FNAMEINDEX], dob);
        int creditsCompleted = ZEROCREDITS;
        if(checks){
            creditsCompleted = Integer.parseInt(parsedCommand[CREDITINDEXENROLLED]);
        }
        EnrollStudent newEnrolledStudent =
                new EnrollStudent(profile, creditsCompleted);

        return newEnrolledStudent;
    }


    /**
     * Executes a command of type A, given a parsed student information.
     * @param parsedCommand an array of strings where each index
     * contains student information as an individual String
     */
    private void executeAR(String[] parsedCommand){
        if(parsedCommand.length <= MISSINGDATA){
            System.out.println("Missing data in command line.");
            return;
        }
        if(parsedCommand.length <= MISSINGDATA
                && parsedCommand[FIRSTINDEX].equals("AT")) {
            System.out.println("Missing data in command line.");
            return;
        }else if(parsedCommand.length <= MISSINGCODE
                && parsedCommand[FIRSTINDEX].equals("AT")){
            System.out.println("Missing the state code.");
            return;
        }
        Student addStudent = makeStudent(parsedCommand, true);
        if(addStudent == null){
            return;
        }
        if(studentRoster.contains(addStudent)) {
            System.out.println(addStudent.getProfile().getFname()
                    + " " + addStudent.getProfile().getLname() +
                    " " + addStudent.getProfile().getDob() + " already in the roster");
        }else{
            studentRoster.add(addStudent);
            System.out.println(addStudent.getProfile().getFname()
                    + " " + addStudent.getProfile().getLname() +
                    " " + addStudent.getProfile().getDob() + " added to the roster");
        }
    }

    /**
     * Prints the type of student that is being enrolled
     * @param tuition The student who is being enrolled and
     * whose type is being found
     * @return A string representing the type of student
     */
    private String typeOfStudent(Student tuition){
        if(tuition instanceof Resident)
            return "(Resident)";
        else if(tuition instanceof TriState)
            return "(Tri-state "+((TriState) tuition).getState().toUpperCase() + ") ";
        else if(tuition instanceof NonResident){
            return "(Non-Resident)";
        }
        else if(tuition instanceof International) {
            if(((International) tuition).isStudyAbroad())
                return "(International studentstudy abroad)";
            else
                return "(International student)";
        }
        return "";
    }

    /**
     * Executes the PT command as stated in the project description;
     * prints the students in enrollment along with their tuition.
     */
    private void executePT(){
        EnrollStudent[] enrollArray = studentEnrollment.getEnrollment();
        Student[] rosterArray = studentRoster.getRoster();
        if(studentEnrollment.isEmpty()){
            System.out.println("Student roster is empty!");
            return;
        }
        System.out.println("** Tuition due **");
        for(int i = 0 ; i < studentEnrollment.getSize(); i++){
            Student stud = null;
            if(enrollArray[i] != null)
                stud = new Resident(enrollArray[i].getProfile(),null,
                        ZEROCREDITS, ZEROSCHOLARSHIP);
            Student tuition = null;
            for(int j = 0; j < rosterArray.length; j++){
                if(enrollArray[i] != null && rosterArray[j] != null
                && rosterArray[j].equals(stud)) {
                    tuition = rosterArray[j];
                }
                if(tuition != null){
                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(FRACTIONDIGITS);
                    df.setMinimumFractionDigits(FRACTIONDIGITS);
                    System.out.print(tuition.getProfile().getFname() +
                            " " + tuition.getProfile().getLname()
                            + " " + tuition.getProfile().getDob() + " ");
                    System.out.print(typeOfStudent(tuition) + " enrolled ");
                    System.out.println(enrollArray[i].getCreditsEnrolled()
                            + " credits: tuition due: $" +
                            df.format(tuition.tuitionDue(enrollArray[i].getCreditsEnrolled())));
                    j = rosterArray.length;
                }
            }
        }
        System.out.println("* end of tuition due *");
    }


    /**
     * Executes the D command as specified in the project writeup.
     * @param parsedCommand an array of strings containing the parsed command.
     */
    private void executeD(String[] parsedCommand){
        EnrollStudent[] enrolledArray = studentEnrollment.getEnrollment();
        EnrollStudent removeStudent = makeEnrolledStudent(parsedCommand, false);
        if(!studentEnrollment.contains(removeStudent)) {
            System.out.println(removeStudent.getProfile().getFname() + " "
                    + removeStudent.getProfile().getLname() +
                    " " + removeStudent.getProfile().getDob() + "is not enrolled.");
            return;
        }
        for(int i = 0; i < studentEnrollment.getSize(); i++){
            if(enrolledArray[i] != null &&
                    enrolledArray[i].equals(removeStudent)) {
                studentEnrollment.remove(removeStudent);
                System.out.println(removeStudent.getProfile().getFname() + " "
                        + removeStudent.getProfile().getLname() +
                        " " + removeStudent.getProfile().getDob() + " dropped");
                return;
            }
        }
    }

    /**
     * This method makes sure that the S command is valid
     * @param parsedCommand The command being checked
     * @return A boolean representing if the command is valid
     * or not
     */
    private boolean checkS(String[] parsedCommand){
        if(parsedCommand.length < MAXLENGTHOFSCOMMAND){
            System.out.println("Missing data in line command.");
            return false;
        } else{
            if(parsedCommand.length == MAXLENGTHOFS){
                if (!isInteger(parsedCommand[PRICE])) {
                    System.out.println("Amount is not an integer.");
                    return false;
                } else if (Integer.parseInt(parsedCommand[PRICE])
                        > RESIDENTSCHOLARSHIPAMOUNT || Integer.parseInt
                        (parsedCommand[PRICE]) <= ZEROCREDITS) {
                    System.out.println(Integer.parseInt(parsedCommand[PRICE])
                            + ": invalid amount.");
                    return false;
                }else{
                    return true;
                }
            }
        }
        return true;
    }


    /**
     * Executes the S command as specified in the project writeup.
     * @param parsedCommand an array of strings containing the parsed command.
     */
    private void executeS(String[] parsedCommand){
        int scholarschipAmount = ZEROSCHOLARSHIP;
        if(!(checkS(parsedCommand)))
            return;
        if(parsedCommand.length == MAXLENGTHOFSCOMMAND)
            scholarschipAmount = SCHOLARSHIPAMOUNT;
        else
            scholarschipAmount =  Integer.parseInt(parsedCommand[PRICE]);
        Profile candidateProfile = new Profile(parsedCommand[LNAMEINDEX]
                ,parsedCommand[FNAMEINDEX], new Date(parsedCommand[DOBINDEX]));
        for(int i = 0; i < studentRoster.getSize(); i++){
            if(studentRoster.getRoster()[i] != null &&
                    candidateProfile.compareTo(studentRoster.getRoster()[i].getProfile())
                            == SAMEPROFILE && studentRoster.getRoster()[i].isResident()){
                EnrollStudent copyStudent =
                        new EnrollStudent(studentRoster.getRoster()[i].getProfile(), ZEROCREDITS);
                EnrollStudent student = studentEnrollment.findStudent(copyStudent);
                if(student.getCreditsEnrolled() < PARTTIME){
                    System.out.println(candidateProfile.toString() +
                            " part time student is not eligible for the scholarship.");
                    return;
                }else {
                    ((Resident) (studentRoster.getRoster()[i])).setScholarship(
                            scholarschipAmount);
                    System.out.println(candidateProfile.toString()
                            + " : scholarship amount updated.");
                    return;
                }
            }else if(studentRoster.getRoster()[i] != null &&
                    candidateProfile.compareTo(studentRoster.getRoster()[i].getProfile())
                            == SAMEPROFILE && !studentRoster.getRoster()[i].isResident()){
                System.out.println(candidateProfile.toString()
                        + " (Non Resident)" + " is not eligible for the scholarship.");
                return;
            }
        }
        System.out.println(candidateProfile.toString() + " not in roster");
    }


    /**
     * Executes a command of type E, given a parsed enrolled student information
     * @param parsedCommand an Array of Strings where each index
     * contains enrolled student information as an individual String
     */
    private void executeE(String[] parsedCommand){
        Student testStudent = makeStudent(parsedCommand, false);
        if(!studentRoster.contains(testStudent)) {
            System.out.println("Cannot Enroll: " + testStudent.getProfile().toString()
            + " is not in the roster.");
            return;
        }
        EnrollStudent addStudent = makeEnrolledStudent(parsedCommand, true);
        if(addStudent == null)
            return;
        if(studentEnrollment.contains(addStudent)) {
            studentEnrollment.remove(addStudent);
            studentEnrollment.add(addStudent);
        }else{
            studentEnrollment.add(addStudent);
        }
        System.out.println(addStudent.getProfile().getFname() +
                " " + addStudent.getProfile().getLname() +
                " " + addStudent.getProfile().getDob() +
                " enrolled " + addStudent.getCreditsEnrolled() + " credits");
    }


    /**
     * This method executes the R command, which removes a provided student.
     * Prints out an error message if there is no valid student.
     * @param parsedCommand an array of Strings containing the tokenized command.
     */
    private void executeR(String[] parsedCommand){
        Date parsedDate = new Date(parsedCommand[DOBINDEX]);
        Profile parsedProfile = new Profile(parsedCommand[LNAMEINDEX],
                parsedCommand[FNAMEINDEX], parsedDate);
        Student studentCopy = new Resident(parsedProfile, null,
                ZEROCREDITS, ZEROSCHOLARSHIP);
        if(studentRoster.remove(studentCopy)){
            System.out.println(studentCopy.getProfile().toString() +
                    " removed from the roster.");
        }else{
            System.out.println(studentCopy.getProfile().toString() +
                    " is not in the roster.");
        }
    }


    /**
     * This method changes the major of specified student as picked
     * intended by the input
     * @param parsedCommand an array of Strings containing
     * the tokenized command
     */
    private void executeC(String[] parsedCommand){
        Student[] rosterCopy = studentRoster.getRoster();
        Date parsedDate = new Date(parsedCommand[DOBINDEX]);
        Profile parsedProfile = new Profile(parsedCommand[LNAMEINDEX],
                parsedCommand[FNAMEINDEX], parsedDate);
        Student studentToChange = new Resident(parsedProfile, null,
                ZEROCREDITS, ZEROSCHOLARSHIP);
        String newMajor=parsedCommand[MAJORINDEX];
        Major maj;
        if(!(studentRoster.contains(studentToChange))) {
            System.out.println(studentToChange.getProfile().getFname() + " "
                    + studentToChange.getProfile().getLname() + " " +
                    studentToChange.getProfile().getDob().toString() + " is not in the roster.");
            return;
        }
        if(isValidMajor(newMajor) && studentRoster.contains(studentToChange)){
            maj = Major.valueOf(parsedCommand[MAJORINDEX].toUpperCase());
            for(int i = 0; i < rosterCopy.length; i++){
                if(rosterCopy[i] != null && rosterCopy[i].equals(studentToChange)){
                    studentToChange = new Resident(parsedProfile, maj,
                            rosterCopy[i].getCreditCompleted(), ZEROSCHOLARSHIP);
                    studentRoster.remove(rosterCopy[i]);
                    studentRoster.add(studentToChange);
                }
            }
            System.out.println(studentToChange.getProfile().getFname() + " "
                    + studentToChange.getProfile().getLname() + " " +
                    studentToChange.getProfile().getDob().toString() + " major changed to "
                    + newMajor);
        }
    }


    /**
     * This method executes the PE command.
     */
    public void executePE(){
        EnrollStudent[] enroll = studentEnrollment.getEnrollment();
        if(studentEnrollment.isEmpty()){
            System.out.println("Enrollment is empty!");
        }else{
            System.out.println("** Enrollment **");
            for(int i = 0; i < studentEnrollment.getSize(); i++){
                if(enroll[i] != null)
                    System.out.println(enroll[i].getProfile().getFname() + " " +
                            enroll[i].getProfile().getLname() +
                            " " + enroll[i].getProfile().getDob()
                            + ": credits enrolled: " + enroll[i].getCreditsEnrolled());

            }
            System.out.println("* end of enrollment *");
        }
    }


    /**
     * Executes the SE command.
     * @param parsedCommand, an array of strings containing the parsed command.
     */
    public void executeSE(String[] parsedCommand){
        System.out.println("Credit completed has been updated.");
        if(parsedCommand.length > SEARGUMENTS){
            System.out.println("SE only takes one argument");
            return;
        }
        System.out.println("** list of students eligible for graduation **");
        for(int i = 0; i < studentRoster.getSize(); i++){
            if(studentRoster.getRoster()[i] != null){
                EnrollStudent studentDesired =
                        new EnrollStudent(studentRoster.getRoster()[i].getProfile(), ZEROCREDITS);
                if(studentEnrollment.contains(studentDesired)){
                    EnrollStudent studentFound = studentEnrollment.findStudent(studentDesired);
                    int creditsCompleted = studentFound.getCreditsEnrolled();
                    studentRoster.getRoster()[i].setCreditCompleted(creditsCompleted +
                            studentRoster.getRoster()[i].getCreditCompleted());
                    if(studentRoster.getRoster()[i].getCreditCompleted() >= GRADCREDITS){
                        System.out.println(studentRoster.getRoster()[i].toString());
                    }

                }
            }
        }
//        System.out.println("** end of graduation list **");
    }


    /**
     * Executes the LS command to load students from a file.
     * @param parsedCommand, an array of strings containing a parsed command.
     */
    public void executeLS(String[] parsedCommand){
        if(parsedCommand.length != LENGTHOFLS){
            System.out.println("LS Command only has two arguments!");
        }else{
            try{
                Scanner textScanner = new Scanner(new File("." +
                        "/studentList.txt"));
                System.out.println("Students loaded to the roster.");
                while(textScanner.hasNext()) {
                    String studentToAdd = textScanner.nextLine();
                    String[] tokenizedCommand = studentToAdd.split(",");
                    Student newStudent = makeStudent(tokenizedCommand,true);
                    if(newStudent == null){
                        return;
                    }
                    if(!studentRoster.contains(newStudent)){
                        studentRoster.add(newStudent);
                    }else{
                        System.out.println(newStudent.getProfile().toString() +
                                " is already in the roster!");
                    }
                }
            }catch (FileNotFoundException e){
                System.out.println(e.toString());
            }
        }
    }


    /**
     * This method executes a command based on the string input.
     * @param parsedCommand an array of strings containing the parsed command
     */
    public void executeCommand(String[] parsedCommand){
        String commandToExecute = parsedCommand[FIRSTINDEX];
        switch (commandToExecute){
            case "LS":
                executeLS(parsedCommand);
                break;
            case "AR":
                executeAR(parsedCommand);
                break;
            case "AN":
                executeAR(parsedCommand);
                break;
            case "AT":
                executeAR(parsedCommand);
                break;
            case "AI":
                executeAR(parsedCommand);
                break;
            case "E":
                executeE(parsedCommand);
                break;
            case "D":
                executeD(parsedCommand);
                break;
            case "S":
                executeS(parsedCommand);
                break;
            case "SE":
                executeSE(parsedCommand);
                break;
        }
    }


    /**
     * This method executes a command based on the string input.
     * @param parsedCommand an array of strings containing the parsed command
     */
    public void executePrintCommands(String[] parsedCommand){
        String commandToExecute = parsedCommand[FIRSTINDEX];
        switch (commandToExecute) {
            case "PE":
                executePE();
                break;
            case "PT":
                executePT();
                break;
            case "P":
                studentRoster.print();
                break;
            case "PS":
                studentRoster.printByStanding();
                break;
            case "PC":
                studentRoster.printBySchoolMajor();
                break;
            case "R":
                executeR(parsedCommand);
                break;
            case "C":
                executeC(parsedCommand);
                break;
            case " ":
                break;
        }
    }


    /**
     * This method runs the tuition manager by listening for commands. This is
     * done using the scanner class and parsing the string input. In addition,
     * we check for invalid input using the checkInvalid method.
     */
    public void run(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Tuition Manager running...");
        while(true){
            String command = reader.nextLine();
            String[] tokenizedCommand = command.split("\\s+");
            if(command.equals("Q")){
                System.out.println("Tuition Manager terminated.");
                break;
            }else if(checkInvalid(command) && command.length() != EMPTYCOMMAND){
                System.out.println(tokenizedCommand[FIRSTINDEX] +
                        " is an invalid command!");
            }else{
                executeCommand(tokenizedCommand);
                executePrintCommands(tokenizedCommand);
            }
        }
        reader.close();
    }
}

