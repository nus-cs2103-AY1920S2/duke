package duke.interaction;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

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

    /**
     * Prints the input with a set indentation.
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
        showLine();
        printWithIndent("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Prints the exception for the user to see.
     * @param e of the exception that occurred.
     */
    public static void showError(Exception e) {
        showLine();
        printWithIndent(e.getMessage());
        showLine();
    }

    /**
     * Prints the information of the new task added
     * and the new number of tasks.
     * @param added task to be print.
     * @param total new count of tasks.
     */
    public static void showTaskAdded(String added, int total) {
        showLine();
        printWithIndent("Got it. I've added this task:");
        printWithIndent(added);
        printWithIndent("Now you have " + total + " task"
                + (total != 1 ? "s" : "") + " in the list.");
        showLine();
    }

    /**
     * Prints the information of all the tasks in the TaskList object.
     * @param taskList for access to the collection of Task objects.
     */
    public static void showAllTasks(TaskList taskList) {
        showLine();
        ArrayList<Task> listToShow = taskList.getList();
        if (!listToShow.isEmpty()) {
            for (int i = 1; i <= listToShow.size(); i++) {
                printWithIndent(i + "." + listToShow.get(i - 1).toString());
            }
        } else {
            printWithIndent("Empty List. You are currently free! Upz lah!");
        }
        showLine();
    }

    /**
     * Prints the information of the done task.
     * @param done task to be print.
     */
    public static void showTaskDone(Task done) {
        showLine();
        printWithIndent("Nice! I've marked this task as done:");
        printWithIndent(done.toString());
        showLine();
    }

    /**
     * Prints the information of the deleted task
     * and the new number of tasks.
     * @param deleted task to be print.
     * @param total new count of tasks.
     */
    public static void showTaskDelete(String deleted, int total) {
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
        showLine();
        printWithIndent("Sorry, mate! No such task.");
        showLine();
    }

    /**
     * Reads next user's whole command and returns as a String.
     * @return string representation of the command.
     */
    public static String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
