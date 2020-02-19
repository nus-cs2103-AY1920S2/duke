package duke.interaction;

import duke.task.Task;
import duke.task.TaskList;
import duke.command.Command;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Contains mainly static methods for all interactions
 * with the user, including printing and reading input.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class Ui {
    private static String indentation = "    ";
    private static String hori_line = "______________________________________";
    private static String latestResponse = ""; // For GUI to read from

    /**
     * Prints the input with a set indentation.
     *
     * @param toPrint user whats to print.
     */
    public static void printWithIndent(String toPrint) {
        System.out.println(indentation + toPrint);
    }

    /**
     * Prints a horizontal line for presentation.
     */
    public static void showLine() {
        printWithIndent(hori_line);
    }

    /**
     * Prints greeting presentation for user.
     */
    public static void greet() {
        showLine();
        printWithIndent("Hello! I'm Duke.");
        printWithIndent("What can I do for you?");
        showLine();
    }

    /**
     * Prints exiting presentation for user.
     */
    public static void sayBye() {
        latestResponse = "Bye. Hope to see you again soon!";
        showLine();
        printWithIndent(latestResponse);
        showLine();
    }

    /**
     * Prints the exception for the user to see.
     *
     * @param e of the exception that occurred.
     */
    public static void showError(Exception e) {
        latestResponse = e.getMessage();
        showLine();
        printWithIndent(e.getMessage());
        showLine();
    }

    /**
     * Prints the information of the new task added
     * and the new number of tasks.
     *
     * @param added task to be print.
     * @param total new count of tasks.
     */
    public static void showTaskAdded(String added, int total) {
        latestResponse = "Got it. I've added this task:\n";
        latestResponse += added + "\n";
        latestResponse += "Now you have " + total + " task"
                + (total != 1 ? "s" : "") + " in the list.";

        showLine();
        printWithIndent("Got it. I've added this task:");
        printWithIndent(added);
        printWithIndent("Now you have " + total + " task"
                + (total != 1 ? "s" : "") + " in the list.");
        showLine();
    }

    /**
     * Prints the information of all the tasks in the TaskList object.
     *
     * @param taskList for access to the collection of Task objects.
     */
    public static void showAllTasks(TaskList taskList) {
        latestResponse = "";
        showLine();
        ArrayList<Task> listToShow = taskList.getList();
        if (!listToShow.isEmpty()) {
            for (int i = 1; i <= listToShow.size(); i++) {
                latestResponse += i + "." + listToShow.get(i - 1).toString() + "\n";
                printWithIndent(i + "." + listToShow.get(i - 1).toString());
            }
        } else {
            latestResponse = "Empty List. You are currently free! Upz lah!";
            printWithIndent(latestResponse);
        }
        showLine();
    }

    /**
     * Prints the information of the done task.
     *
     * @param done task to be print.
     */
    public static void showTaskDone(Task done) {
        latestResponse = "Nice! I've marked this task as done:\n";
        latestResponse += done.toString();
        showLine();
        printWithIndent("Nice! I've marked this task as done:");
        printWithIndent(done.toString());
        showLine();
    }

    /**
     * Prints the information of the undone task.
     *
     * @param undone task to be print.
     */
    public static void showTaskUndone(Task undone) {
        latestResponse = "Ok, I've marked this task as undone:\n";
        latestResponse += undone.toString();
        showLine();
        printWithIndent("Ok, I've marked this task as undone:");
        printWithIndent(undone.toString());
        showLine();
    }

    /**
     * Prints the information of the deleted task
     * and the new number of tasks.
     *
     * @param deleted task to be print.
     * @param total new count of tasks.
     */
    public static void showTaskDelete(String deleted, int total) {
        latestResponse = "Noted! I've removed this task:\n";
        latestResponse += deleted + "\n";
        latestResponse += "Now you have " + total
                + " task" + (total != 1 ? "s" : "") + " in the list.";

        showLine();
        printWithIndent("Noted! I've removed this task:");
        printWithIndent(deleted);
        printWithIndent("Now you have " + total
                + " task" + (total != 1 ? "s" : "") + " in the list.");
        showLine();
    }

    /**
     * Prints to the user that the task of interest was not found.
     */
    public static void showTaskNotFound() {
        latestResponse = "Sorry, mate! No such task.";

        showLine();
        printWithIndent(latestResponse);
        showLine();
    }

    /**
     * Prints to the user that there is nothing to undo.
     */
    public static void showNothingToUndo() {
        latestResponse = "Nothing to undo.";

        showLine();
        printWithIndent(latestResponse);
        showLine();
    }

    /**
     * Reads next user's whole command and returns as a String.
     *
     * @return string representation of the command.
     */
    public static String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static String getLatestResponse() {
        return latestResponse;
    }

    public static void setLatestResponse(String response) {
        latestResponse = response;
    }

    /**
     * Prints the commands provided by the application.
     *
     * @param commands varargs representing all commands to be printed.
     */
    public static void showAllCommands(Command... commands) {
        latestResponse = "";
        showLine();
        Stream.of(commands).forEach(command -> {
            latestResponse += "-- " + command.toString() + "\n";
            printWithIndent("-- " + command + ". " + command.toString());
        });
        showLine();
    }
}
