package duke.ui;

import java.util.Scanner;
import duke.exception.DukeException;

public class Ui {
    private Scanner scan = new Scanner(System.in);
    private String dukeLogo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints out standard welcome message.
     */
    public void welcomeUser() {
        System.out.println("Hello, I am \n" + dukeLogo + "your personal buddy. What's up?\n"
                + "____________________________________________________________\n");
    }

    /**
     * Prints out standard farewell message.
     */
    public void farewellUser() {
        divider("Aww, see you again soon!");
        return;
    }

    /**
     * Prints out input String within two divider lines. If input string is empty, prints out single line.
     * @param s String to be wrapped by two divider lines.
     */
    public void divider(String s) {
        if (s.length() == 0) {
            System.out.println("____________________________________________________________\n");
        } else {
            System.out.println("____________________________________________________________\n" + s + "\n"
                    + "____________________________________________________________\n");
            return;
        }
    }

    /**
     * Reads user input.
     * @return User input.
     */
    public String readCommand() {
        String scanned = scan.nextLine();
        return scanned;
    }

    /**
     * Prints input String.
     * @param s Input String.
     */
    public void showMessage(String s) {
        System.out.println(s);
    }

    /**
     * Prints given DukeException.
     * @param e Input DukeException.
     */
    public void showError(DukeException e) {
        System.out.println(e.toString());
    }

}
