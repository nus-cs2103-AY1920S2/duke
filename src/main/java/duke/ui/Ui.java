package duke.ui;

import java.util.Scanner;

/**
 * UI of the application.
 */
public class Ui {

    protected Scanner sc;

    /**
     * Constructs an User Interface.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a top horizontal line before every executed command.
     */
    public void showTopLine() {
        System.out.println("\n    ________________________________________________________");
    }

    /**
     * Prints a bottom horizontal line after every executed command.
     */
    public void showBottomLine() {
        System.out.println("    ________________________________________________________\n");
    }

    /**
     * Prints Welcome message whenever Duke has been started up.
     */
    public void showWelcome() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showTopLine();
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        showBottomLine();
    }

    /**
     * Shows the loading error when there is error loading from disk.
     */
    public void showLoadingError() {
        System.out.println("OOPS!!! I couldn't load your tasks from the disk! :-(");
    }

    /**
     * Shows the message of DukeException.
     *
     * @param message the error message.
     */
    public void showError(String message) {
        System.out.println("     " + message);
    }

    /**
     * Reads command from user input.
     *
     * @return the line of command input by user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the acknowledgement message after executing the commands.
     *
     * @param acknowledgement The acknowledgement message to be printed.
     */
    public void showAcknowledgement(String acknowledgement) {
        System.out.println(acknowledgement);
    }

    /**
     * Prints the reminder for deadlines.
     *
     * @param reminder the list of overdue deadlines and upcoming deadlines.
     */
    public void showReminder(String reminder) {
        if (reminder.isBlank()) {
            return;
        }
        showTopLine();
        System.out.println(reminder);
        showBottomLine();
    }
}
