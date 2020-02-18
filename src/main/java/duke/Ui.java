package duke;

import duke.tasks.Task;

/**
 * User Interface class which is centred about a strong user experience and its associated methods.
 * Mainly uses public static methods to format Strings for easy identification and usage.
 */
public class Ui {

    /**
     * Gives the String to greet the user with instructions on how to use Duke.
     *
     * @return String to greet the user with instructions on how to use Duke.
     */
    public static String welcome() {
        return "Hello, E-1337 here! :D I am an android crafted specifically "
            + "by my inventor Eugene to help you humans out with your memory.\n"
            + "\n"
            + "I'm feeling good and ready to go! What can I do for you today?\n"
            + "\n"
            + "If you're unsure, here are some things I can do:\n"
            + "1. Add a Todo [T]\n"
            + "2. Add an Event [E]\n"
            + "3. Add a Deadline [D]\n"
            + "4. Record a Loan [L] \n"
            + "5. List all your Tasks and Loans\n"
            + "6. Mark tasks as Done [V]\n"
            + "7. Delete Tasks on your List\n"
            + "8. Find Tasks by Keyword\n"
            + "9. Say Goodbye\n"
            + "\n"
            + "If you're unfamiliar on how to use me, try entering 'help'\n";
    }

    /**
     * Gives the String to say farewell to the user, for now.
     *
     * @return String to say farewell to the user, for now.
     */
    public static String goodbye() {
        return "====> Alright byeee thanks for coming and see ya soon! <==== \n:D :D :D :D :D\n"
            + "Please exit by clicking the big red X button, or I'll close it for you soon.";
    }

    /**
     * Gives the String to provide the user with instructions on how to use Duke.
     *
     * @return String of instructions on how to use Duke.
     */
    public static String help() {
        return "Here, you can do the following:\n"
                + "1. todo <task name> (adds a Task to do)\n"
                + "2. event <event name> /at <event date> (adds an event with a date)\n"
                + "3. deadline <deadlined task name> /by <deadline date> (adds a task with a deadline)\n"
                + "4. loan <person> /value <monetary amount>\n"
                + "5. list (lists down all current tasks)\n"
                + "6. done <task number> (marks task labelled <task number> as done)\n"
                + "7. delete <task number> (deletes task labelled <task number> from the list) or simply delete all\n"
                + "8. find <keywords separated by space> (finds the tasks in the list with the keyword(s)) "
                + "which can be space-separated\n"
                + "9. bye (Say bye to you :) )\n"
                + "10. help\n"
                + "Note that all dates follow the format YYYY-MM-DD\n"
                + "\n"
                + "PS: A '[V]' indicates a completed task while a '[X]' indicates an incomplete task";
    }

    /**
     * Gives String indicating removal of a given task, of its index then and the current number of Tasks remaining.
     *
     * @param index index of the Task to remove.
     * @param removedTask the Task which we are to remove.
     * @param allTasks TaskList containing all the Tasks in Duke.
     * @return String indicating removal of a given Task, of its index then and the current number of Tasks remaining.
     */
    public static String taskRemovalMessage(Task removedTask, TaskList allTasks) {
        return "Removed Task: " + removedTask
                + "\nHope it's worth it!\nYou are now left with " + allTasks.sizeOf() + " tasks.";
    }

    /**
     * Gives the String for all Tasks, their number order, and their completion status for the list command.
     */
    public static String listMessage(String tasks) {
        return tasks;
    }

    /**
     * Gives a String response to indicate Task is done, especially after the done command after doTask() completes.
     *
     * @param t Task that has been completed via doTask method.
     */
    public static String taskCompleteMessage(Task t) {
        if (t.getIsDone()) {
            return "That's already done, try another. Or did you make a careless mistake? XD";
        } else {
            return "Nice! The following task has been marked completed:\n"
                    + "===> [V] " + t + " <===";
        }
    }

    /**
     * Gives an exception message for an Exception.
     *
     * @param exceptionType String for the exception message.
     */
    public static String exceptionMessage(String exceptionType) {
        return exceptionType;
    }

    /**
     * Provides a given String.
     *
     * @param customMessage String of interest.
     */
    public static String customMessage(String customMessage) {
        return customMessage;
    }

}
