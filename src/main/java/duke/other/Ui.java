package duke.other;

import duke.task.*;
import java.util.Scanner;

/**
 * Represents the UI of Duke.
 */
public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);

    public Ui() {
    }

    /**
     * Prints the welcome message when the user runs Duke.
     */
    public void welcome() {
        String greeting = "Hey there! I'm DingDing!\n"
                + "    What's up today? ;D";
        printWithBorder(greeting);
    }

    /**
     * Reads the user input and returns it.
     * @return The user's input
     */
    public String readCommand() {
        return SCANNER.nextLine();
    }

    /**
     * Prints out the error message.
     * @param errMessage Error message
     */
    public void showError(String errMessage) {
            System.out.println(errMessage);
    }

    /**
     * Prints out the loading error.
     */
    public void showLoadingError() {
        System.out.println("    No task list found! Creating new task list...");
    }

    /**
     * Prints out the feedback when a task is added and the task added.
     * @param task The added task
     * @param taskList TaskList to add the task into
     */
    public static void taskAdded(Task task, TaskList taskList) {
        System.out.println("    Alright! Task added:\n      " + task.toString() + "\n    Now you have " + taskList.getSize()
                + " task(s) in your list!");
    }

    /**
     * Throws a DukeException when there is an invalid input for the deadline command.
     * @throws DukeException If there is an invalid input for the deadline command
     */
    public static void deadlineInputError() throws DukeException {
        throw new DukeException("    Invalid date and/or time format! \n" +
                "    Specify deadline with: /at <YYYY/MM/DD> <HH:MM>\n" +
                "    i.e. deadline Project Meeting /by 2020/01/28 18:00");
    }

    /**
     * Throws a DukeException when there is an invalid input for the event command.
     * @throws DukeException If there is an invalid input for the event command
     */
    public static void eventInputError() throws DukeException {
        throw new DukeException("    Invalid date and/or time format! \n" +
                "    Specify event with: /at <YYYY/MM/DD> <HH:MM>\n" +
                "    i.e. event Project Meeting /at 2020/01/28 18:00");
    }

    /**
     * Throws a DukeException when there is an invalid input for the todo command.
     * @throws DukeException If there is an invalid input for the todo command
     */
    public static void todoInputError() {
        throw new DukeException("    Specify your todo \n" + "    i.e. todo Complete tutorials ");
    }

    /**
     * Throws a DukeException when there is an invalid input for the date command.
     * @throws DukeException If there is an invalid input for the date command
     */
    public static void dateInputError() throws DukeException {
        throw new DukeException("    Please enter a valid date in <YYYY/M/D> format\n" +
                "    i.e. 2020/10/28");
    }

    /**
     * Throws a DukeException when there is an invalid input for the done command.
     * @throws DukeException If there is an invalid input for the done command
     */
    public static void doneInputError() throws DukeException{
        throw new DukeException("    Task doesn't exist!");
    }

    /**
     * Throws a DukeException when there is an invalid input for the delete command.
     * @throws DukeException If there is an invalid input for the delete command
     */
    public static void deleteInputError() throws DukeException {
        throw new DukeException("    Task doesn't exist! Add a new task!");
    }

    /**
     * Prints the border line
     */
    public static void showLine() {
        System.out.println("    ###################################################");
    }

    /**
     * Prints the message in between border lines
     * @param message Message to be printed between 2 border lines
     */
    public static void printWithBorder(String message) {
        System.out.println("    ###################################################\n"
                + "    " + message + "\n"
                + "    ###################################################\n");
    }
}

