package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * This class handles the command-line interface of {@code Duke}.
 */
class Ui {
    private static final int HORIZONTAL_LINE_LENGTH = 76;

    /**
     * Returns a string representation of the Duke logo.
     *
     * @return the {@code Duke} logo
     */
    String getLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return logo;
    }

    /** Returns a welcome greeting.
     *
     * @return a welcome greeting
     */
    String sayHello() {
        return "Hello, I'm Duke!\nWhat can I do for you?";
    }

    /** Returns a farewell greeting.
     *
     * @return a farewell greeting
     */
    String sayBye() {
        return "Goodbye. Hope to see you again soon!";
    }

    /** Returns user input from the specified {@code Scanner}.
     *
     * @param sc the scanner
     * @return the user input
     */
    String getInput(Scanner sc) {
        return sc.nextLine();
    }

    String outputTask(Task task, int numberOfTasks) {
        String status;
        if (numberOfTasks == 1) {
            status = "There is now 1 task in the list.";
        } else {
            status = "There are now " + numberOfTasks + " tasks in the list.";
        }
        return String.format("Got it. I've added this task:\n  %s\n%s", task, status);
    }

    /** Returns the detail message string of the exception.
     *
     * @param e the exception
     * @return the detail message of the exception
     */
    String outputException(DukeException e) {
        return e.getMessage();
    }

    /** Formats the message.
     *
     * @param message the message to be formatted
     * @return the formatted message
     */
    String format(String message) {
        String horizontalLine = new String(new char[HORIZONTAL_LINE_LENGTH]).replace("\0", "-");
        message = horizontalLine + "\n" + message + "\n" + horizontalLine;
        return message.lines()
            .map(x -> "    " + x + "\n")
            .reduce("", (x, y) -> x + y);
    }

    /** Prints the message to standard output.
     *
     * @param message the message to be printed
     */
    void print(String message) {
        System.out.println(message);
    }
}
