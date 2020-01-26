package duke.interaction;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static String indentation = "    ";
    private static String hori_line = "______________________________________";

    public static void PrintWithIndent(String toPrint) {
        System.out.println(indentation + toPrint);
    }

    public static void ShowLine() {
        PrintWithIndent(hori_line);
    }

    public static void Greet() {
        ShowLine();
        PrintWithIndent("Hello! I'm Duke");
        PrintWithIndent("What can I do for you?");
        ShowLine();
    }

    public static void SayBye() {
        ShowLine();
        PrintWithIndent("Bye. Hope to see you again soon!");
        ShowLine();
    }

    public static void ShowError(Exception e) {
        ShowLine();
        PrintWithIndent(e.getMessage());
        ShowLine();
    }

    public static void ShowTaskAdded(Task added, int total) {
        ShowLine();
        PrintWithIndent("Got it. I've added this task:");
        PrintWithIndent(added.toString());
        PrintWithIndent("Now you have " + total + " task"
                + (total != 1 ? "s" : "") + " in the list.");
        ShowLine();
    }

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

    public static void ShowTaskDone(Task done) {
        ShowLine();
        PrintWithIndent("Nice! I've marked this task as done:");
        PrintWithIndent(done.toString());
        ShowLine();
    }

    public static void ShowTaskDelete(String deleted, int total) {
        ShowLine();
        PrintWithIndent("Noted! I've removed this task:");
        PrintWithIndent(deleted);
        PrintWithIndent("Now you have " + total
                + " task" + (total != 1 ? "s" : "") + " in the list.");
        ShowLine();
    }

    public static void ShowTaskNotFound() {
        ShowLine();
        PrintWithIndent("Sorry, mate! No such task.");
        ShowLine();
    }

    public static String ReadCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
