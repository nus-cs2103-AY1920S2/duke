package com.duke.util;

import com.duke.tag.Tag;
import com.duke.task.Task;
import com.duke.task.TaskList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the handler of inputs and outputs of the user interface.
 */
public class Ui {
    private static final String SPACE = "     ";
    private static final String LINE = SPACE + "_____________________________________________";
    Scanner scanner = new Scanner(System.in);

    /**
     * Prints the error message to the user to communicate that
     * there is a problem loading data from storage.
     */
    public void showLoadingError() {
        System.out.println(getLoadingErrorMessage());
    }

    /**
     * Outputs a String of the error message to the user to communicate that
     * there is a problem loading data from storage.
     * @return the error message to the user to communicate that
     *     there is a problem loading data from storage.
     */
    public String getLoadingErrorMessage() {
        return SPACE + "OOPS! There is a problem loading save file";
    }

    /**
     * Prints the welcome message when Duke starts up.
     */
    public void showWelcome() {
        System.out.println(getWelcomeMessage());

    }

    /**
     * Gets the welcome message when Duke starts up.
     * @return A string of the welcome message when Duke starts up.
     */
    public String getWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String out = "Hello from\n" + logo + "\n" + "Hello! I'm Duke" + "\n" + SPACE + "What can I do for you?";
        return out;
    }

    /**
     * Prints a line which is used for the formatting of the user interface.
     */
    public void showLine() {
        System.out.println(getLine());
    }

    /**
     * Gets a line which is used for the formatting of the user interface.
     * @return A line which is used for the formatting of the user interface.
     */
    public String getLine() {
        return LINE;
    }

    /**
     * Prints the error message associated with a <code>DukeException</code>DukeException object.
     * Invoked when a DukeException is caught.
     *
     * @param msg the error message associated with a <code>DukeException</code>DukeException object.
     */
    public void showError(String msg) {
        System.out.println(getErrorMessage(msg));
    }

    /**
     * Gets the error message associated with a <code>DukeException</code>DukeException object.
     * Invoked when a DukeException is caught.
     * @param msg the error message associated with a <code>DukeException</code>DukeException object.
     * @return the error message associated with a <code>DukeException</code>DukeException object.
     */
    public String getErrorMessage(String msg) {
        return SPACE + msg;
    }

    /**
     * Invokes the user to input the next command and converts it to a <code>String</code> object.
     *
     * @return The user input of the next command as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the farewell message when Duke terminates.
     */
    public void showGoodbye() {
        System.out.println(getGoodbyeMessage());
    }

    /**
     * Gets the farewell message when Duke terminates.
     * @return The farewell message when Duke terminates.
     */
    public String getGoodbyeMessage() {
        return SPACE + "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the list of tasks on the current task list. Invoked when
     * the command input is "list".
     *
     * @param tasks the <code>TaskList</code> object that stores the list of
     *              tasks in the current session.
     */
    public void showList(TaskList tasks) {
        System.out.println(getListMessage(tasks));
    }

    /**
     * Gets the list of tasks on the current task list. Invoked when
     * the command input is "list".
     *
     * @param tasks the <code>TaskList</code> object that stores the list of
     *              tasks in the current session.
     * @return the list of tasks on the current task list.
     */
    public String getListMessage(TaskList tasks) {
        String output = SPACE + "Here are the tasks in your list: ";
        int noOfTasks = tasks.tasks.size();
        for (int i = 0; i < noOfTasks; i++) {
            int index = i + 1;
            output += "\n" + SPACE + index + ". " + tasks.tasks.get(i);
        }
        return output;
    }

    /**
     * Prints the message that the <code>Task</code> t is successfully marked as done.
     *
     * @param t The <code>Task</code> to be marked as done.
     */
    public void showDone(Task t) {
        System.out.println(getDoneMessage(t));
    }

    /**
     * gets the message that the <code>Task</code> t is successfully marked as done.
     * @param t The <code>Task</code> to be marked as done.
     * @return The message that the <code>Task</code> t is successfully marked as done.
     */
    public String getDoneMessage(Task t) {
        String output = SPACE + "Nice! I've marked this task as done: "
                + "\n" + SPACE + t;
        return output;
    }

    /**
     * Prints the message that a task t is deleted from the task list, and
     * indicate the number of task left on the list.
     *
     * @param t     the task of be removed from the list.
     * @param count the number of task left on the task list.
     */
    public void showDelete(Task t, int count) {
        System.out.println(getDeleteMessage(t,count));
    }

    /**
     * Prints the message that a task t is deleted from the task list, and
     * indicate the number of task left on the list.
     * @param t     the task of be removed from the list.
     * @param count the number of task left on the task list.
     * @return The message that a task t is deleted from the task list, and
     *     indicate the number of task left on the list.
     */
    public String getDeleteMessage(Task t, int count) {
        String output = SPACE + "Noted. I've removed this task: "
                + "\n" + SPACE + "  " + t + "\n" + SPACE + "Now you have " + count
                + " tasks in your list.";
        return output;
    }

    /**
     * Prints the message that a task t is added to the task list, and
     * indicate the number of task on the list.
     *
     * @param t     The task of be added to the list.
     * @param count The number of task on the task list.
     */
    public void showAdd(Task t, int count) {
        System.out.println(getAddMessage(t, count));
    }

    /**
     * Gets the message that a task t is added to the task list, and
     * indicate the number of task on the list.
     * @param t     The task of be added to the list.
     * @param count The number of task on the task list.
     * @return The message that a task t is added to the task list.
     */
    public String getAddMessage(Task t, int count) {
        String out = SPACE + "Got it. I've added this task: " + "\n" + SPACE
                + "  " + t + "\n" + SPACE + "Now you have " + count
                + " tasks in your list.";
        return out;
    }

    /**
     * Prints the list of tasks found in the storage that contains the
     * keyword specified in a Find command.
     * @param tasks The list of tasks that contain the keyword.
     */
    public void showFind(ArrayList<Task> tasks) {
        System.out.println(getFindMessage(tasks));
    }

    /**
     * Gets the list of tasks found in the storage that contains the
     * keyword specified in a Find command.
     * @param tasks The list of tasks that contain the keyword.
     * @return The list of tasks found in the storage that contains the
     *     keyword specified in a Find command.
     */
    public String getFindMessage(ArrayList<Task> tasks) {
        String out = SPACE + "Here are the matching tasks in your list: ";
        int index = 1;
        for (Task t : tasks) {
            out += "\n" + SPACE + index + ". " + t;
            index++;
        }
        return out;
    }

    /**
     * Prints the message that a tag has been associated with a task.
     * @param tag The tag relevant in this tagging event.
     * @param t   The task relevant in this tagging event.
     */
    public void showTagTaskMessage(Tag tag, Task t) {
        System.out.println(getTagTaskMessage(tag, t));
    }

    /**
     * Gets the message that a tag has been successfully associated with a task.
     * @param tag The tag relevant in this tagging event.
     * @param t   The task relevant in this tagging event.
     * @return A String representation of the tagging event.
     */
    public String getTagTaskMessage(Tag tag, Task t) {
        String out = SPACE + "I have tagged the following task with tag '" + tag.getTagName() + "': "
                + "\n" + SPACE + t;
        return out;
    }

    /** Prints the list of tasks found in the storage that contains the
     * tag specified in the command.
     * @param tag The tag added to the task.
     */
    public void showFindTagMessage(Tag tag) {
        System.out.println(getFindTagMessage(tag));
    }

    /**
     * Prints the list of tasks found in the storage that contains the
     * tag specified in the command.
     * @param tag The tag added to the task.
     * @return The string representation of the message.
     */
    public String getFindTagMessage(Tag tag) {
        if (tag == null) {
            return "The tag is not recognized.";
        }

        String out = SPACE + "Here are the tasks with tag '" + tag.getTagName() + "': ";
        List<Task> tasks = tag.getTasks();
        for (Task t:tasks) {
            out += "\n" + SPACE + t;

        }
        return out;
    }
}
