package duke.ui;

import duke.exception.DukeException;

import java.util.Scanner;

public class Ui {
    private Scanner scan = new Scanner(System.in);
    private String dukeLogo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints out standard welcome message.
     */
    public void welcomeUser() {
        System.out.println("Hello, I am \n" + dukeLogo + "your personal buddy. What's up?\n"
                + "______________________________________________________\n");
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
    public String divider(String s) {
        return s;
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
    public String showMessage(String s) {
        System.out.println(s);
        return s;
    }

    /**
     * Prints given DukeException.
     * @param e Input DukeException.
     */
    public String showError(DukeException e) {
        System.out.println(e.toString());
        return e.toString();
    }

}
