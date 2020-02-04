import java.util.Scanner;

/**
 * Ui class handles user input.
 */
public class Ui {

    private Scanner sc;

    /**
     * Creates a Ui (user interface) that handles user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message.
     */
    public static void showWelcome() {
        String tinga = "       _    _  \n"
                + "     _| |_ (_)\n"
                + "    |_   _\\ _  _ __   ____   __ _\n"
                + "      | |  | \\| '  \\ / _  \\ / _` |\n"
                + "      | |  | || || || |_| || (_| |\n"
                + "      |_|  |_||_||_| \\__  | \\__,_|\n"
                + "                     /\\_| |\n"
                + "                     \\____/\n";
        showLine();
        System.out.println(tinga);
        System.out.println("    Hey there! I'm tinga :)");
        System.out.println("    What can I do for you?");
        showLine();
    }

    /**
     * Returns string with welcome message.
     * @return string containing welcome message.
     */
    public static String showWelcomeString() {
        String tinga = "       _    _  \n"
                + "     _| |_ (_)\n"
                + "    |_   _\\ _  _ __   ____   __ _\n"
                + "      | |  | \\| '  \\ / _  \\ / _` |\n"
                + "      | |  | || || || |_| || (_| |\n"
                + "      |_|  |_||_||_| \\__  | \\__,_|\n"
                + "                     /\\_| |\n"
                + "                     \\____/\n";
        tinga += "Hey there! I'm tinga :)\n";
        tinga += "What can I do for you?\n";
        return tinga;
    }

    /**
     * Prints a line.
     */
    public static void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Returns true if scanner has a next line, and false if otherwise.
     * @return boolean.
     */
    public boolean hasNextLine() {
        return sc.hasNextLine();
    }

    /**
     * Reads user input.
     * @return string of user's input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints loading error where file is not loaded.
     */
    public void showLoadingError() {
        System.out.println("     ☹ OOPS!!! File not loaded.");
    }

    /**
     * Prints error.
     * @param error error message.
     */
    public void showError(String error) {
        System.out.println("     ☹ OOPS!!! " + error);
    }
}