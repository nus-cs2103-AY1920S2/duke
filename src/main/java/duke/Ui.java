package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the Ui that deals with interactions with user.
 */
public class Ui {

    protected Scanner sc;

    /**
     * Constructs a Ui with Scanner being initialised.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        this.showLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        this.showLine();
    }

    /**
     * Prints the exit message.
     */
    public void showExit() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    /**
     * Prints the error message.
     *
     * @param msg The error message to be shown to user.
     */
    public void showError(String msg) {
        System.out.println("     " + msg);
    }

    /**
     * Prints the error message if failure to load tasks from storage.
     */
    public void showLoadingError() {
        this.showLine();
        System.out.println("     OOPS!!! Fail to load data.");
        this.showLine();
    }

    /**
     * Reads the user input command.
     *
     * @return The user input command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a horizontal dotted line.
     */
    public void showLine() {
        System.out.println("    ----------------------------------------------------------");
    }

    /**
     * Print the messages that is properly formatted.
     *
     * @param messages The array of messages to be formatted and printed.
     */
    public void showMessages(String[] messages) {
        for (String s : messages) {
            System.out.println("     " + s);
        }
    }

    /**
     * Print the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public void showList(ArrayList<Task> tasks) {
        System.out.println("     Here are the tasks in your list:");
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.get(i).toString());
        }
    }
}
