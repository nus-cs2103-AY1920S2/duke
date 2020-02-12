import java.util.Scanner;
import java.util.ArrayList;

import dukeexception.DukeException;

import task.Task;

/**
 * Deals with interactions with the user (mainly output).
 * Methods which return a string of every possible messages user will see.
 */
public class Ui {
    Scanner sc = new Scanner(System.in);
    String divider = "____________________________________________________________";

    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Makes output msg of the entire list of tasks from TaskList.
     *
     * @param tasks TaskList objects.
     * @return The tasks as a string nicely formatted.
     */
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

    /**
     * Makes output msg of task marked as done.
     *
     * @param task The task that was marked as done.
     * @return Output msg of task marked done as a string.
     */
    public String showMarkedDone(Task task) {
        String doneMsg = "Nice! I've marked this task as done:\n"
                + task + "\n";
        return doneMsg;
    }

    /**
     * Makes output msg of task added to the list.
     *
     * @param task Task added to the list.
     * @param numOfTasks The current number of tasks in the list after adding the task.
     * @return Output msg of task added as a string.
     */
    public String showAdded(Task task, int numOfTasks) {
        String addedMsg = "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + numOfTasks + " tasks in the list.\n";
        return addedMsg;
    }

    /**
     * Makes output msg of task deleted from the list.
     *
     * @param task Task deleted from the list.
     * @param numOfTasks The current number of tasks in the list after deleting the task.
     * @return Output msg of task deleted as a string.
     */
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

    /**
     * Makes output msg of the tasks found from an ArrayList of tasks found.
     *
     * @param tasksFound ArrayList of tasks found.
     * @return Tasks found as a string nicely formatted.
     */
    public String showFound(ArrayList<Task> tasksFound) {
        String foundMsg = "Here are the tasks found with keyword in your list:\n";
        for (int i = 0; i < tasksFound.size(); i++) {
            foundMsg += i + 1 + "." + tasksFound.get(i) + "\n";
        }
        return foundMsg;
    }
}