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

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
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

    public String showDeletedTask(TaskList tasks, int option) {
        return "Noted. I've removed this task: \n  " + tasks.arr.get(option - 1) + "\nNow you have " + (tasks.arr.size() - 1) + " tasks in the list.";
    }

    public String showAddedTask(Task task, TaskList tasks) {
        return "Got it. I've added this task: \n " + task + "\nNow you have " + tasks.arr.size() + " tasks in the list.";
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
        for (int i = 0; i < list.arr.size(); i++) {
            s += i + 1 + ". " + list.arr.get(i) + "\n";
        }
        return s;
    }

    public String showDateError() {
        return "Please key in the date in the YYYY-MM-DD format instead!!!";
    }
}
