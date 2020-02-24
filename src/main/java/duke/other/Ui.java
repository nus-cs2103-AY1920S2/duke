package duke.other;

import duke.task.Task;
import duke.task.TaskList;

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
    public void showWelcome() {
        String greeting = "    Hey there! I'm DingDing!\n"
                + "    What's up today? ;D";
        printWithBorder(greeting);
    }

    /**
     * Reads the user input and returns it.
     *
     * @return The user's input
     */
    public String readCommand() {
        return SCANNER.nextLine();
    }

    /**
     * Prints out the error message.
     *
     * @param errMessage Error message
     */
    public String showError(String errMessage) {
        System.out.println(errMessage);
        return errMessage;
    }

    /**
     * Prints out the loading error.
     */
    public String showLoadingError() {
        String print = "    No task list found! Creating new task list...";
        System.out.println(print);
        return print;
    }

    /**
     * Prints out the feedback when a task is added and the task added.
     *
     * @param task     The added task
     * @param taskList TaskList to add the task into
     */
    public static String showTaskAdded(Task task, TaskList taskList) {
        String print = "    Alright! Task added:\n      " + task.toString() + "\n    Now you have "
                + taskList.getSize() + " task(s) in your list!";
        System.out.println(print);
        return print;
    }

    /**
     * Prints the border line.
     */
    public static void showLine() {
        System.out.println("    ###################################################");
    }

    /**
     * Prints the message in between border lines.
     *
     * @param message Message to be printed between 2 border lines
     */
    public static void printWithBorder(String message) {
        showLine();
        System.out.println(message + "\n");
        showLine();
    }

    /**
     * Returns an error message when there is an invalid input for the deadline command.
     */
    public static String deadlineInputError() {
        String msg = "    Invalid date and/or time format! \n"
                + "    Specify deadline with: /by <YYYY/MM/DD> <HH:MM>\n"
                + "    i.e. deadline Project Meeting /by 2020/01/28 18:00";
        System.out.println(msg);
        return msg;
    }

    /**
     * Returns an error message when there is an invalid input for the event command.
     *
     */
    public static String eventInputError() {
        String msg = "    Invalid date and/or time format! \n"
                + "    Specify event with: /at <YYYY/MM/DD> <HH:MM>\n"
                + "    i.e. event Project Meeting /at 2020/01/28 18:00";
        System.out.println(msg);
        return msg;
    }

    /**
     * Returns an error message when there is an invalid input for the todo command.
     *
     */
    public static String todoInputError() {
        String msg = "    Specify your todo \n" + "    i.e. todo Complete tutorials ";
        System.out.println(msg);
        return msg;
    }

    /**
     * Returns an error message when there is an invalid input for the date command.
     *
     */
    public static String dateInputError() {
        String msg = "    Please enter a valid date in <YYYY/M/D> format\n"
                + "    i.e. 2020/10/28";
        System.out.println(msg);
        return msg;
    }

    /**
     * Returns an error message when there is an invalid input for the done command.
     *
     */
    public static String doneInputError() {
        String msg = "    Task doesn't exist!";
        System.out.println(msg);
        return msg;
    }

    /**
     * Returns an error message when there is an invalid input for the delete command.
     *
     */
    public static String deleteInputError() {
        String msg = "    Task doesn't exist! Add a new task!";
        System.out.println(msg);
        return msg;
    }
}

