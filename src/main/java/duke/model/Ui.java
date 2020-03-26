package duke.model;

/**
 * The <code>Ui</code> class is a utility class which deals with printing to standard output to
 * provide an interface to interact with the user.
 *
 * @author Zhu Yijie
 */
public class Ui {
    private static final String LOGO = "d8888b. db    db db   dD d88888b \n"
        + "88  `8D 88    88 88 ,8P' 88'     \n"
        + "88   88 88    88 88,8P   88ooooo \n"
        + "88   88 88    88 88`8b   88~~~~~ \n"
        + "88  .8D 88b  d88 88 `88. 88.     \n"
        + "Y8888D' ~Y8888P' 88   8b 988888P\n";
    
    public static String getHelloMessage() {
        String welcomeMsg = LOGO + "Hello, I'm Duke! What can I do for you?";
        return welcomeMsg;
    }

    /**
     * Prints a welcome message to standard output.
     *
     * <p>Used when starting up program.
     */
    public static void sayHello() {
        System.out.println(parseOutput(getHelloMessage()));
    }
    
    /**
     * Prints to standard output.
     *
     * @param contents String to be output.
     */
    public static void print(String contents) {
        System.out.println(parseOutput(contents));
    }

    /**
     * Handles program output and formats it for printing to standard output.
     *
     * @param output String to be output.
     * @return The string to be printed to standard output.
     */
    public static String parseOutput(String output) {
        if (output.isEmpty()) {
            return "";
        }
        
        return output + "\n";
    }
}
