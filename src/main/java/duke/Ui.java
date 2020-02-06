package duke;

import duke.tasks.Task;

/**
 * User Interface class which is centred about a strong user experience and its associated methods.
 * Mainly provides public static methods to represent Strings for easy identification and usage.
 */
public class Ui {

    /**
     * Gives the String to greet the user with instructions on how to use Duke.
     *
     * @return String to greet the user with instructions on how to use Duke.
     */
    public static String welcome() {
        return Constant.FORMAT_LINE
            + "Hello, duke.Duke here! :D \n"
            + "I'm feeling good and ready to go!\n"
            + "What can I do for you?\n"
            + "Here, you can do the following:\n"
            + "1. todo <task name> (adds a Task to do)\n"
            + "2. event <event name> /at <event date> (adds an event with a date)\n"
            + "3. deadline <deadlined task name> /by <deadline date> (adds a task with a deadline)\n"
            + "4. list (lists down all current tasks)\n"
            + "5. done <task number> (marks task labelled <task number> as done)\n"
            + "6. delete <task number> (deletes task labelled <task number> from the list)\n"
            + "7. find <keywords separated by space> (finds the tasks in the list with the keyword(s)) \n"
            + "8. bye (duke says bye to you :) )\n"
            + Constant.FORMAT_LINE;
    }

    /**
     * Gives the String to say farewell to the user, for now.
     *
     * @return String to say farewell to the user, for now.
     */
    public static String goodbye() {
        return Constant.FORMAT_LINE
            + "====> Alright byeee thanks for coming and see ya soon! <==== \n:D :D :D :D :D"
            + Constant.FORMAT_LINE;
    }

    /**
     * Gives String indicating removal of a given task, of its index then and the current number of Tasks remaining.
     *
     * @param index index of the Task to remove.
     * @param removedTask the Task which we are to remove.
     * @param allTasks TaskList containing all the Tasks in Duke.
     * @return String indicating removal of a given Task, of its index then and the current number of Tasks remaining.
     */
    public static String taskRemovalMessage(int index, Task removedTask, TaskList allTasks) {
        return Constant.FORMAT_LINE
                + "Removed Task #" + (index + 1) + ": " + removedTask
                + "\nHope it's worth it!\nYou are now left with " + allTasks.sizeOf() + " tasks."
                + Constant.FORMAT_LINE;
    }

    /**
     * Gives the String for all Tasks, their number order, and their completion status for the list command.
     */
    public static String listAllTasksMessage(TaskList allTasks) {
        String result = Constant.FORMAT_LINE;
        for (int i = 0; i < allTasks.sizeOf(); i++) {
            result += obtainTaskFromStoredMessage(i, allTasks) + "\n";
        }
        result += Constant.FORMAT_LINE;
        return result;
    }

    /**
     * Gives a String response to indicate Task is done, especially after the done command after doTask() completes.
     *
     * @param t Task that has been completed via doTask method.
     */
    public static String taskCompleteMessage(Task t) {
        String result = Constant.FORMAT_LINE;
        if (t.getIsDone()) {
            result += "That's already done, try another. Or did you make a careless mistake? XD";
        } else {
            result += "Nice! The following task has been marked completed:\n"
                    + "===> [V] " + t + " <===";
        }
        result += Constant.FORMAT_LINE;
        return result;
    }

    /**
     * Gives the String representing an individual Task with current completion status, without formatting lines.
     *
     * @param i index of storage of the Task in the container/collection.
     * @param allTasks TaskList containing all the non-deleted Tasks for Duke.
     * @return String representing an individual Task with current completion status, without formatting lines.
     */
    public static String obtainTaskFromStoredMessage(int i, TaskList allTasks) {
        String tickOrCross = allTasks.getTask(i).obtainStatusIcon();
        return String.valueOf(i + 1) + ". [" + tickOrCross + "] " + allTasks.getTask(i);
    }

    /**
     * Gives an exception message for an Exception.
     *
     * @param e String for the exception message.
     */
    public static String exceptionMessage(String e) {
        return Constant.FORMAT_LINE + e + "\n" + Constant.FORMAT_LINE;
    }

    /**
     * Provides a given String.
     *
     * @param s String of interest.
     */
    public static String customMessage(String s) {
        return s;
    }

}
