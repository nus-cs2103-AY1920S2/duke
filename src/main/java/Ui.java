import java.util.Scanner;
import java.util.ArrayList;

import dukeexception.DukeException;

import task.Task;

/**
 * Deals with interactions with the user.
 * Methods with System.out.print for every possible messages user will see.
 */
public class Ui {
    Scanner sc = new Scanner(System.in);
    String divider = "____________________________________________________________";

    public String readCommand() {
        return sc.nextLine();
    }

    public String showList(TaskList tasks) {
        String listMsg = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getLength(); i++) {
            listMsg += i + 1 + "." + tasks.getTask(i) + "\n";
        }
        return listMsg;
    }

    public String showBye() {
        String byeMessage = "Bye. Hope to see you again soon!\n";
        return byeMessage;
    }

    public String showHello() {
        String helloMessage = "Hello! I'm Duke\nWhat can I do for you?\n";
        return helloMessage;
    }

    public String showMarkedDone(Task task) {
        String doneMsg = "Nice! I've marked this task as done:\n"
                + task + "\n";
        return doneMsg;
    }

    public String showAdded(Task task, int numOfTasks) {
        String addedMsg = "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + numOfTasks + " tasks in the list.\n";
        return addedMsg;
    }

    public String showDeleted(Task task, int numOfTasks) {
        String deletedMsg = "Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + numOfTasks + " tasks in the list.\n";
        return deletedMsg;
    }

    public String showError(DukeException e) {
        String errorMsg = e.getMessage() + "\n";
        return errorMsg;
    }

    public String showFound(ArrayList<Task> tasksFound) {
        String foundMsg = "Here are the tasks found with keyword in your list:\n";
        for (int i = 0; i < tasksFound.size(); i++) {
            foundMsg += i + 1 + "." + tasksFound.get(i) + "\n";
        }
        return foundMsg;
    }
}