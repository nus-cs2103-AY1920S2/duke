import java.util.Scanner;
import java.util.ArrayList;

import dukeexception.DukeException;

import javafx.util.Pair;
import task.Task;

/**
 * Deals with interactions with the user (mainly output).
 * Methods which return a string of every possible messages user will see.
 */
public class Ui {
    Scanner sc = new Scanner(System.in);

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

    /**
     * Hello message consists of all the commands user can input.
     *
     * @return Entry hello msg
     */
    public String showHello() {
        String helloMessage = "Hello! I'm Park Seo Jun Oppa!\n"
                + "To see all tasks, type list\n"
                + "Other commands are:\n"
                + "- todo (description)\n"
                + "- deadline or event (description) /by (time)\n"
                + "- done or delete (task number)\n"
                + "- find (keyword)\n"
                + "- update (task number) (D or T depending on description or time) (change)\n\n"
                + "If you ever need help, just type help and Oppa will be here to help!\n"
                + "Type bye when you're done with oppa.";
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
     * @param tasksFound ArrayList of Pair containing taskNum and Task found.
     * @return Tasks found as a string nicely formatted.
     */
    public String showFound(ArrayList<Pair<Integer, Task>> tasksFound) {
        String foundMsg = "Here are the tasks found with keyword in your list:\n";
        for (int i = 0; i < tasksFound.size(); i++) {
            foundMsg += tasksFound.get(i).getKey() + "." + tasksFound.get(i).getValue() + "\n";
        }
        return foundMsg;
    }

    /**
     * Makes output msg of updated task.
     *
     * @param task Updated task.
     * @return Output msg of updated task as a string.
     */
    public String showUpdated(Task task) {
        String updatedMsg = "Alright. I've updated the task to:\n"
                + task + "\n";
        return updatedMsg;
    }
}