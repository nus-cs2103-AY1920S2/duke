package duke;

import java.util.Scanner;

/**
 * UI class.
 */
public class Ui {

    /**
     * Display a welcome text.
     */
    public static void showWelcome() {
        System.out.println("Hello! I'm duke\nWhat can I do for you?");
    }

    /**
     * Display the duke Logo.
     */
    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints out a line.
     */
    public void printLine() {
        System.out.println("--------------------------------");
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
    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out the task message.
     * @param t takes in a task
     * @param i takes in task list size
     */
    public void printTodoComplete(Task t, int i) {
        System.out.println("Got it. I've added this task");
        System.out.println(t.toString());
        System.out.println("Now you have " + i + " tasks in the list");
    }
}
