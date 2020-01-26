package duke.interaction;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static String indentation = "    ";
    private static String hori_line = "______________________________________";

    /**
     * Prints the input with a set indentation.
     * @param toPrint user whats to print.
     */
    public static void PrintWithIndent(String toPrint) {
        System.out.println(indentation + toPrint);
    }

    /**
     * Prints a horizontal line for presentation.
     */
    public static void ShowLine() {
        PrintWithIndent(hori_line);
    }

    /**
     * Prints greeting presentation for user.
     */
    public static void Greet() {
        ShowLine();
        PrintWithIndent("Hello! I'm Duke");
        PrintWithIndent("What can I do for you?");
        ShowLine();
    }

    /**
     * Prints exiting presentation for user.
     */
    public static void SayBye() {
        ShowLine();
        PrintWithIndent("Bye. Hope to see you again soon!");
        ShowLine();
    }

    /**
     * Prints the exception for the user to see.
     * @param e of the exception that occurred.
     */
    public static void ShowError(Exception e) {
        ShowLine();
        PrintWithIndent(e.getMessage());
        ShowLine();
    }

    /**
     * Prints the information of the new task added
     * and the new number of tasks.
     * @param added task to be print.
     * @param total new count of tasks.
     */
    public static void ShowTaskAdded(Task added, int total) {
        ShowLine();
        PrintWithIndent("Got it. I've added this task:");
        PrintWithIndent(added.toString());
        PrintWithIndent("Now you have " + total + " task"
                + (total != 1 ? "s" : "") + " in the list.");
        ShowLine();
    }

    /**
     * Prints the information of all the tasks in the TaskList object.
     * @param taskList for access to the collection of Task objects.
     */
    public static void ShowAllTasks(TaskList taskList) {
        ShowLine();
        ArrayList<Task> task_list = taskList.getList();
        if (!task_list.isEmpty()) {
            for (int i = 1; i <= task_list.size(); i++) {
                PrintWithIndent(i + "." + task_list.get(i - 1).toString());
            }
        } else {
            PrintWithIndent("Empty List. You are currently free! Upz lah!");
        }
        ShowLine();
    }

    /**
     * Prints the information of the done task.
     * @param done task to be print.
     */
    public static void ShowTaskDone(Task done) {
        ShowLine();
        PrintWithIndent("Nice! I've marked this task as done:");
        PrintWithIndent(done.toString());
        ShowLine();
    }

    /**
     * Prints the information of the deleted task
     * and the new number of tasks.
     * @param deleted task to be print.
     * @param total new count of tasks.
     */
    public static void ShowTaskDelete(String deleted, int total) {
        ShowLine();
        PrintWithIndent("Noted! I've removed this task:");
        PrintWithIndent(deleted);
        PrintWithIndent("Now you have " + total
                + " task" + (total != 1 ? "s" : "") + " in the list.");
        ShowLine();
    }

    /**
     * Prints to the user that the task of interest was not found.
     */
    public static void ShowTaskNotFound() {
        ShowLine();
        PrintWithIndent("Sorry, mate! No such task.");
        ShowLine();
    }

    /**
     * Reads next user's whole command and returns as a String.
     * @return string representation of the command.
     */
    public static String ReadCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
