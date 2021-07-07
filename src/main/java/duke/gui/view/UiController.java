package duke.gui.view;

import duke.entity.TaskList;
import duke.entity.task.Task;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UiController {

    public UiController() {

    }


    /**
     * Say welcome to the user upon initializing programme
     */
    public String showWelcome() {
        String string = "";
        string += ("Hello! I'm duke\n");
        string += ("What can I do for you?\n");
        return string;
    }

    /**
     * Say bye to user upon terminating programme
     */
    public String sayBye() {
        String string = "";
        string += ("Bye. Hope to see you again soon!\n");
        return string;
    }

    /**
     * Prints a line to separate different sections
     */
    public String showLine() {
        String string = "";
        string += ("____________________________\n");
        return string;
    }



    /**
     * Prints a message confirming the addition of a task
     *
     * @param task Task that was added
     * @param taskList current TaskList being used in the programme
     */
    public String confirmAddTask(Task task, TaskList taskList) {
        String string = "";
        string += ("Got it. I've added this task:\n");
        string += ("  " + task.printTask() + "\n");
        string += ("Now you have " + taskList.getTasks().size() + " tasks in the list.\n");
        return string;
    }

    /**
     * Prints a message confirming the deletion of a task
     *
     * @param task Task that was deleted
     * @param taskList current TaskList being used in the programme
     */
    public String confirmDeleteTask(Task task, TaskList taskList) {
        String string = "";
        string += ("Noted. I've removed this task:\n");
        string += ("  " + task.printTask() + "\n");
        string += ("Now you have " + taskList.getTasks().size() + " tasks in the list.\n");
        return string;
    }

    /**
     * Prints a message confirming the completion of a task
     *
     * @param index index of task that was completed
     * @param task task that was completed
     */
    public String confirmDoneTask(int index, String task) {
        String string = "";
        string += ("Nice! I've marked this task as done:\n");
        string += (index + 1 + "." + task + "\n");
        return string;
    }

    /**
     * Prints a message prompting that an invalid format has been entered
     */
    public String invalidFormatError() {
        String string = "";
        string += ("Invalid format for input!\n");
        return string;
    }

    /**
     * Prints a message prompting that an error has been encountered when saving to file
     */
    public String errorSavingChanges() {
        String string = "";
        string += ("Error saving changes!\n");
        return string;
    }

    /**
     * Prints all tasks currently saved
     *
     * @param tasks TaskList currently being used by programme
     */
    public String listAllTasks(TaskList tasks) {
        String string = "";
        List<Task> taskList = tasks.getTasks();
        for (int i = 0; i < taskList.size(); i++) {
            string += (i + 1 + "." + taskList.get(i).printTask() + "\n");
        }
        return string;
    }

    /**
     * Prints all tasks due by a certain date
     *
     * @param tasks list of tasks that are due
     * @param date due date specified by user
     */
    public String listDueTasks(List<Task> tasks, Date date) {
        String string = "";
        string += ("Here is a list of events and deadlines: \n");
        for (int i = 0; i < tasks.size(); i++) {
            string += (i + 1 + "." + tasks.get(i).printTask() + "\n");
        }
        return string;
    }

    /**
     * Prints a customer message determined by programme that doesn't fall under any of the other categories
     *
     * @param message message to be printed
     */
    public String printCustomMessage(String message) {
        String string = "";
        string += (message);
        return string;
    }

    /**
     * Prompts user that details are missing from their command
     */
    public String missingDetails() {
        String string = "";
        string += ("☹ OOPS!!! Information about the task is missing\n");
        return string;
    }

    /**
     * Prints all tasks that matches a user-input keyword
     *
     * @param tasks list of tasks that are found
     */
    public String listFoundTasks(List<Task> tasks) {
        String string = "";
        string += ("Here are the matching tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            string += (i + 1 + "." + tasks.get(i).printTask() + "\n");
        }
        return string;
    }

}
