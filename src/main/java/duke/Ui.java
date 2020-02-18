package duke;

import task.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class takes care of the user interface
 * especially printing outputs to the user.
 **/
public class Ui {

    /**
     * Constructs the UI object.
     **/
    Ui(){
    }

    /**
     * Shows an error message of loading a file to the user, when loading throws an IOException.
     * @param e The exception to be shown.
     * @return Message of the error.
     **/
    String showLoadingError(IOException e) {
        return e.toString();
    }

    /**
     * Shows an error message of loading a file to the user, when loading throws a DukeException.
     * @param e The exception to be shown.
     * @return Message of the error.
     **/
    String showLoadingError(DukeException e) {
        return e.toString();
    }

    /**
     * Shows the task lists of the user.
     * @return Message to show task list to the user.
     **/
    public static String showList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            return "There are no tasks in your list";
        }
        sb.append("Here are the tasks in your list:\n");
        sb.append(Ui.listOfTask(tasks));
        return sb.toString();
    }

    /**
     * Shows the task schedule at a certain date of the user.
     * @return Message to show task schedule
     * at a certain date to the user.
     **/
    public static String showSchedule(TaskList tasks, LocalDate date) {
        String dateFormat = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            return "There are currently no tasks for " + dateFormat + ".";
        }
        sb.append("Here are the schedule for " + dateFormat + ":\n");
        sb.append(Ui.listOfTask(tasks));
        return sb.toString();
    }

    private static String listOfTask (TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task s : tasks.getTasks()) {
            sb.append(i + "." + s.toString() + "\n");
            i++;
        }
        return sb.toString();
    }

    /**
     * Shows confirmation of task adding to the user.
     * @return The confirmation message about task adding.
     **/
    public static String showTask(Task newTask, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append(newTask + "\n");
        sb.append("Now you have " + size + " tasks in the list.\n");
        return sb.toString();
    }

    /**
     * Prints confirmation of changing task done status to done
     * to the user.
     * @return The confirmation message about finished task.
     **/
    public static String showDone(Task updatedTask) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(updatedTask + "\n");
        return sb.toString();
    }

    /**
     * Shows a farewell message to the user.
     * @return Farewell message.
     **/
    public static String showBye() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bye. Hope to see you again soon!");
        return sb.toString();
    }

    /**
     * Shows confirmation of removing task from the list
     * to the user.
     * @param removedTask Task that are to be removed from the list.
     * @param size The new size of the list after task removal.
     * @return Confirmation message of task removal.
     **/
    public static String showRemove(Task removedTask, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(removedTask + "\n");
        sb.append("Now you have " + size + " tasks in the list.\n");
        return sb.toString();
    }

    /**
     * Shows an exception message to the user.
     * @param e The exception.
     * @return Message of the exception.
     **/
    public static String showException(DukeException e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e);
        return sb.toString();
    }

    /**
     * Shows the tasks that has related keywords.
     * @param foundTasks List of tasks found with related keywords
     * @return list of tasks with related keyword
     **/
    public static String showFindings(TaskList foundTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int i = 1;
        for (Task s : foundTasks.getTasks()) {
            sb.append(i + "." + s.toString() + "\n");
            i++;
        }
        return sb.toString();
    }
}
