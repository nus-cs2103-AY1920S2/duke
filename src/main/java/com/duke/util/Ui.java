package com.duke.util;

import com.duke.task.Task;
import com.duke.task.TaskList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the handler of inputs and outputs of the user interface.
 */
public class Ui {
    private String space = "     ";
    private String line = space + "_____________________________________________";
    Scanner scanner = new Scanner(System.in);

    /**
     * Prints the error message to the user to communicate that
     * there is a problem loading data from storage.
     */
    public void showLoadingError() {
        System.out.println(space + "OOPS! There is a problem loading save file");
    }

    /**
     * Prints the welcome message when Duke starts up.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke" + "\n" + space + "What can I do for you?");

    }

    /**
     * Prints a line which is used for the formatting of the user interface.
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Prints the error message associated with a <code>DukeException</code>DukeException object.
     * Invoked when a DukeException is caught.
     *
     * @param msg the error message associated with a <code>DukeException</code>DukeException object.
     */
    public void showError(String msg) {
        System.out.println(space + msg);
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
        System.out.println(space + "Bye. Hope to see you again soon!");
    }

    /**
     * Prints the list of tasks on the current task list. Invoked when
     * the command input is "list".
     *
     * @param tasks the <code>TaskList</code> object that stores the list of
     *              tasks in the current session.
     */
    public void showList(TaskList tasks) {
        String output = space + "Here are the tasks in your list: ";
        int noOfTasks = tasks.tasks.size();
        for (int i = 0; i < noOfTasks; i++) {
            int index = i + 1;
            output += "\n" + space + index + ". " + tasks.tasks.get(i);
        }
        System.out.println(output);
    }

    /**
     * Prints the message that the <code>Task</code> t is successfully marked as done.
     *
     * @param t the <code>Task</code> to be marked as done.
     */
    public void showDone(Task t) {
        String output = space + "Nice! I've marked this task as done: "
                + "\n" + space + t;
        System.out.println(output);
    }

    /**
     * Prints the message that a task t is deleted from the task list, and
     * indicate the number of task left on the list.
     *
     * @param t     the task of be removed from the list.
     * @param count the number of task left on the task list.
     */
    public void showDelete(Task t, int count) {
        String output = space + "Noted. I've removed this task: "
                + "\n" + space + "  " + t + "\n" + space + "Now you have " + count
                + " tasks in your list.";
        System.out.println(output);
    }

    /**
     * Prints the message that a task t is added to the task list, and
     * indicate the number of task on the list.
     *
     * @param t     the task of be added to the list.
     * @param count the number of task on the task list.
     */
    public void showAdd(Task t, int count) {
        String out = space + "Got it. I've added this task: " + "\n" + space
                + "  " + t + "\n" + space + "Now you have " + count
                + " tasks in your list.";
        System.out.println(out);
    }

    /**
     * Prints the list of tasks found in the storage that contains the
     * keyword specified in a Find command.
     * @param tasks The list of tasks that contain the keyword.
     */
    public void showFind(ArrayList<Task> tasks) {
        String out = space + "Here are the matching tasks in your list: ";
        int index = 1;
        for (Task t : tasks) {
            out += "\n" + space + index + ". " + t;
            index++;
        }
        System.out.println(out);
    }
}
