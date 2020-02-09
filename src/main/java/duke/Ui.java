package duke;

import java.util.Scanner;

/**
 * Receive inputs from the user and interact with him or her.
 */
public class Ui {
    protected Scanner sc;

    /**
     * Ui constructor which initializes the scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Welcome message to be displayed to the user upon startup of the Duke program.
     */
    public String welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return logo + "Hello! What can I do for you?";
    }

    /**
     * Request inputs from the user.
     *
     * @return Returns the input of the user.
     */
    public String receiveInput() {
        return sc.nextLine();
    }

    /**
     * Displays exception messages to user.
     *
     * @param e Exception to be displayed.
     */
    public void exceptionMessage(DukeException e) {
        System.out.println(e);
    }

    /**
     * Displays user messages to user.
     *
     * @param msg Message to be displayed.
     */
    public void userMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        sc.close();
    }

    /**
     * Message to be displayed to the user before the program terminates.
     */
    public void fareWellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
