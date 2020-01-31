package dukeui;

import dukeexceptions.DukeException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents a minial UI program that is used to interact with the user
 * Prints messages as well as obtains input when necessary
 */

public class DukeUI {
    private final Scanner in;
    private final PrintStream out;
    private final String line = "    ____________________________________________________________";
    private final String logo =
            "     ____        _        \n"
            + "    |  _ \\ _   _| | _____ \n"
            + "    | | | | | | | |/ / _ \\\n"
            + "    | |_| | |_| |   <  __/\n"
            + "    |____/ \\__,_|_|\\_\\___|\n";

    public DukeUI() {
        this(System.in, System.out);
    }

    public DukeUI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void printLine() {
        System.out.println(line);
    }

    public void printCustomMessage(String customM) {
        System.out.println(customM);
    }

    /**
     * Prints the welcome message for Duke
     */
    public void showWelcomeMessage() {
        printLine();
        printCustomMessage(logo);
        System.out.println("    Hello I'm Duke");
        System.out.println("    What can I do for you?");
        printLine();
    }

    /**
     * Returns the user's written command as a string
     *
     * @return Input String
     */
    public String readCommandString() {
        return in.nextLine();
    }


    /**
     * Prints out the error message from a DukeException
     *
     * @param e DukeException
     */
    public void showErrorMessage(DukeException e) {
        System.out.println("    " + e.getMessage());
        printLine();
    }
}
