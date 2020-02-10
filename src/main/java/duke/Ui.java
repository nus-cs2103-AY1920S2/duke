package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Responsible for user interaction in terms of returning output to each user.
 */
public class Ui {

    /**
     * Returns message to user to inform a new save file was created, in the case no such file existed initially.
     * @return Returns message to user to inform a new save file was created.
     */
    public String showLoadingError() {
        return "A new save file has been initialised for you." + "\n";
    }

    /**
     * Returns useful message to user on why exception was generated.
     * @param e Contains the message on why exception was generated.
     * @return Returns useful message to user on why exception was generated.
     */
    public String showError(DukeException e) {
        return e.toString();
    }


    /**
     * Returns a welcome message.
     * @return Returns welcome message to user, upon initialisation.
     */
    public static String showWelcomeMessage() {
        return "Hello there! I am Duke, your personal assistant. Do let me know what I can do for you!" + "\n";
    }

    /**
     * Returns a goodbye message.
     * @return Returns final message to user before program is terminated.
     */
    public String showFarewellMessage() {
        return "Adios. It was my pleasure assisting you. Keep smiling." + "\n";
    }

    /**
     * Returns a String of all tasks in taskList object to user.
     * @param taskList a list of tasks inputted by user.
     * @return a String of all tasks specified in the param.
     */
    public String showList(TaskList taskList) {
        return taskList.toString() + "\n";
    }

    /**
     * Returns output of successful Done command.
     * @param completed the task that is marked as completed.
     * @return An output of a completed Done command.
     */
    public String showDoneMessage(Task completed) {
        return "Hooray! You've finally managed to finish this task:" + "\n" + completed.toString() + "\n";
    }

    /**
     * Returns output of a successful Delete Command.
     * @param deleted the task that is to be deleted from taskList.
     * @return An output of a completed Delete command.
     */
    public String showDeleteMessage(Task deleted) {
        return "Got it! I've removed this task:" + "\n" + deleted.toString() + "\n";
    }

    /**
     * Returns output of a Whatsup Command.
     * @param tasks the tasks that are on the same day that is queried.
     * @return An output of a completed Whatsup command.
     */
    public String showWhatsupMessage(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "You have nothing assigned on that day.";
        }
        String result = "The tasks you have on that day are:" + "\n";
        for (Task task: tasks) {
            result += task.toString() + "\n";
        }
        return result;
    }

    /**
     * Returns output of a successful Add command.
     * @param newTask either a Event, Deadline or To-do task object.
     * @return An output of a successful Add command.
     */
    public String showAddTaskMessage(Task newTask) {
        String identifier;
        if (newTask instanceof Todo) {
            identifier = "to do";
        } else if (newTask instanceof Deadline) {
            identifier = "deadline";
        } else {
            identifier = "event";
        }
        return "Awesome! I've added this " + identifier + " :" + "\n" + newTask.toString();
    }

    /**
     * Returns output of a Find Command.
     * @param tasks The list of tasks that match a keyword queried by user.
     * @return A String of tasks that match what user queried.
     */
    public String showFindMessage(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "There were no tasks matching what you said.";
        }
        String result = "Here you go, this is probably what you were finding:" + "\n";
        for (Task task: tasks) {
            result += task.toString() + "\n";
        }
        return result;
    }
}