package projecttwo;

/**
 * This class provides a main method for running the enrollment system.
 * @author Anis Chihoub, Aryan Jairath
 */
public class RunProject2 {

    /**
     * This method is the main method, which will call the run method in TuitionManager.
     * @param args An array of strings for any command line arguments
     */
    public static void main(String[] args) {
        new TuitionManager().run();
    }
}