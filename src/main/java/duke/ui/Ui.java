package duke.ui;


/**
 * The Class Ui provides a basic user interface.
 */
public class Ui {

    /**
     * The logo.
     */
    protected String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Greet with the logo.
     *
     * @return a string to greet and show logo
     */
    public String greetLogo() {
        return "Hello from\n" + this.logo + "\n";
    }

    /**
     * Normal greeting.
     *
     * @return a string to greet
     */
    public String greet() {
        return "Hello! I'm Duke\n" + "What can I do for you?\n";
    }

    /**
     * Prints the input request.
     */
    public void printInputRequest() {
        System.out.println();
        System.out.println("Input Command: ");
    }

    /**
     * Sign off before exiting the program.
     *
     * @return a string to greet bye
     */
    public String bye() {
        return "      Bye. Hope to see you again soon!";
    }

    /**
     * To notify that there is an error in the File.
     *
     * @return an string to denote the file is corrupted
     */
    public String showLoadingError() {
        return "File is corrupted, Start on a new file";
    }
}
