package duke.interaction;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static String indentation = "    ";
    private static String hori_line = "______________________________________";

    public static void printWithIndent(String toPrint) {
        System.out.println(indentation + toPrint);
    }

    public static void showLine() {
        printWithIndent(hori_line);
    }

    public static void greet() {
        showLine();
        printWithIndent("Hello! I'm Duke");
        printWithIndent("What can I do for you?");
        showLine();
    }

    public static void sayBye() {
        showLine();
        printWithIndent("Bye. Hope to see you again soon!");
        showLine();
    }

    public static void showError(Exception e) {
        showLine();
        printWithIndent(e.getMessage());
        showLine();
    }

    public static void showTaskAdded(Task added, int total) {
        showLine();
        printWithIndent("Got it. I've added this task:");
        printWithIndent(added.toString());
        printWithIndent("Now you have " + total + " task"
                + (total != 1 ? "s" : "") + " in the list.");
        showLine();
    }

    public static void showAllTasks(TaskList taskList) {
        showLine();
        ArrayList<Task> task_list = taskList.getList();
        if (!task_list.isEmpty()) {
            for (int i = 1; i <= task_list.size(); i++) {
                printWithIndent(i + "." + task_list.get(i - 1).toString());
            }
        } else {
            printWithIndent("Empty List. You are currently free! Upz lah!");
        }
        showLine();
    }

    public static void showTaskDone(Task done) {
        showLine();
        printWithIndent("Nice! I've marked this task as done:");
        printWithIndent(done.toString());
        showLine();
    }

    public static void showTaskDelete(String deleted, int total) {
        showLine();
        printWithIndent("Noted! I've removed this task:");
        printWithIndent(deleted);
        printWithIndent("Now you have " + total
                + " task" + (total != 1 ? "s" : "") + " in the list.");
        showLine();
    }

    public static void showTaskNotFound() {
        showLine();
        printWithIndent("Sorry, mate! No such task.");
        showLine();
    }

    public static String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
