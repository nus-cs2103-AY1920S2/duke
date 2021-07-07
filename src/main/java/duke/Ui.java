package duke;

import java.util.Scanner;

/**
 * UI class.
 */
public class Ui {

    /**
     * Display a welcome text.
     */
    public static String showWelcome() {
        return "Hello! I'm duke\nWhat can I do for you?";
    }

    /**
     * Display the duke Logo.
     */
    public static String printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo;
    }

    /**
     * Prints out a line.
     */
    public String printLine() {
        return "--------------------------------";
    }

    /**
     * Read in the command.
     * @return the string of the command
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prints out exit message.
     */
    public String printExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out the task message.
     * @param t takes in a task
     * @param i takes in task list size
     */
    public String printTodoComplete(Task t, int i) {
        return "Got it. I've added this task\n" + t.toString()
                + "\nNow you have " + i + " tasks in the list";
    }

    /**
     * Print found message.
     */
    public String printFound() {
        return "Here are the matching tasks in your list:";
    }

    /**
     * Print Not Found message.
     */
    public String printNotFound() {
        return "There was no matching task";
    }

    public String printContactComplete() {
        return "Contact Saved";
    }
}
