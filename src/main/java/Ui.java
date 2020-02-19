import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles the interactions with the user as they input to Duke.
 */
public class Ui {
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static String showWelcome() {
        String logo = " /\\_/\\\n" +
                      "( o.o )\n";
        return logo + "Hello! I'm Momo :3\nWhat can I do for you?";
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public boolean hasInputs() {
        return sc.hasNext();
    }

    public String showError(String message) {
        return message;
    }

    public String showFileNotFoundError() {
        return "This file is not found in the specified directory!! :(";
    }

    public String showIndexError() {
       return "This task option does not exist within the task list. Please try again with an appropriate task index.";
    }

    public String showLoadingError() {
        return "OOPS! An error occurred while loading from given file.";
    }

    public String endDuke() {
        return "Bye. Hope to see you again soon!";
    }

    public String showDoneTask(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    public String showDeletedTask(ArrayList<Task> deletedTasks, int originalSize) {
        String s = "";
        s += "Noted. I've removed this task: \n ";
        for (int i = 0; i < deletedTasks.size(); i++) {
            String task = deletedTasks.get(i).toString() + "\n";
            s += task;
        }
        int size = originalSize - deletedTasks.size();
        String taskOrTasks = "";
        if (size == 1) {
            taskOrTasks = " task";
        } else {
            taskOrTasks = " tasks";
        }
        s += "Now you have " + size + taskOrTasks +  " in the list.";
        return s;
    }

    public String showAddedTask(Task task, TaskList tasks) {
        assert tasks.arr.size() > 0 : "Size of the task list cannot be 0";
        int size = tasks.size();
        String taskOrTasks = "";
        if (size == 1) {
            taskOrTasks = " task";
        } else {
            taskOrTasks = " tasks";
        }
        return "Got it. I've added this task: \n " + task + "\nNow you have " + size + taskOrTasks + " in the list.";
    }

    public String showFoundTasks(ArrayList<Task> tasksFound) {
        String s = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasksFound.size(); i++) {
            s += tasksFound.get(i) + "\n";
        }
        return s;
    }

    public String printList(TaskList list) {
        String s = "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            s += i + 1 + ". " + list.get(i) + "\n";
        }
        return s;
    }

    public String showDateError() {
        return "Please key in the date in the YYYY-MM-DD format instead!!!";
    }
}
