package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Responsible for user interaction in terms of input and output of system.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Prints error in case file is not loaded properly.
     */
    public String showLoadingError() {
        return "A new save file has been initialised for you." + "\n";
    }

    /**
     * Prints useful message to user on why exception was generated.
     * @param e Contains the message on why exception was generated.
     */
    public String showError(DukeException e) {
        return e.toString();
    }


    /**
     * Prints welcome message to user
     * @return the name of user, in String type
     */
    public static String printWelcomeMessage() {
        return "Hello there! I am Duke, your personal assistant. Do let me know what I can do for you!" +"\n";
    }

    /**
     * prints final message to user before program is terminated in Duke class.
     */
    public String printFarewellMessage() {
        return "\t" + "Adios. It was my pleasure assisting you. Keep smiling." +"\n";
    }


    /**
     * outputs all tasks in taskList object to user.
     * @param taskList a list of tasks inputted by user.
     */
    public String printList(TaskList taskList) {
        return taskList.toString() + "\n";
    }

    /**
     * outputs success of done command.
     *
     * @param completed the task that is marked as completed.
     */
    public String printDoneMessage(Task completed) {
        return "\t" + "Hooray! You've finally managed to finish this task:" + "\n" +
                "\t\t" + completed.toString() + "\n";
    }

    /**
     * outputs success of delete command.
     *
     * @param deleted the task that is to be deleted from taskList.
     */
    public String printDeleteMessage(Task deleted) {
        return "\t" + "Got it! I've removed this task:" + "\n" +
        "\t\t" + deleted.toString() + "\n";
    }

    /**
     * outputs success of whatsup command.
     *
     * @param tasks the tasks that are on the same day that is queried.
     */
    public String printWhatsupMessage(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "You have nothing assigned on that day.";
        } else {
            String result = "\t" + "The tasks you have on that day are:" + "\n";
            for (Task task: tasks) {
                result += "\t\t" + task.toString() + "\n";
            }
            return result;
        }
    }

    /**
     * outputs success of add task command.
     *
     * @param newTask either a Event, Deadline or To-do task object
     */
    public String printAddTaskMessage(Task newTask) {
        String identifier;
        if (newTask instanceof Todo) {
            identifier = "to do";
        } else if (newTask instanceof Deadline) {
            identifier = "deadline";
        } else {
            identifier = "event";
        }
        return "\t" + "Awesome! I've added this " + identifier + " :" + "\n" +
                "\t\t" + newTask.toString();
    }

    public String printFindMessage(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "\t" + "There were no tasks matching what you said.";
        } else {
            String result = "\t" + "Here you go, this is probably what you were finding:" + "\n";
            for (Task task: tasks) {
                result += "\t\t" + task.toString() + "\n";
            }
            return result;
        }
    }
}