package duke.ui;
import duke.commons.Task;
import duke.logic.TaskList;

/**
 * Represents the Ui Manager of the program that outputs different <code>String</code> messages.
 */
public class Ui {

    public Ui() {
    }

    public static String greeting() {
        return "Hello my dude! Tell me what you have been procrastinating recently...";
    }

    public String showLoadingError(String message) {
        return "!!! [LOADING]:\n" + message;
    }

    public String showCommandError(String message) {
        return  "!!! [COMMAND]:\n" + message;
    }

    public String showDuplicateError(String message) {
        return "!!! [ADDING]:\n" + message;
    }

    public String suggestHelp() {
        return "You could try your luck again... or type \"help\" instead :)";
    }

    public static String printHelp() {
        return "Dude, you must be so desperate that you are here. But don't worry, I got you!\n" +
                "Here are the commands that I support:\n\n" +
                "1. help: umm you are already here dude, so I guess you know how to use this one..\n" +
                "2. list: list\n" +
                "3. add todo: todo [description]\n" +
                "4. add event: event [description] /at [yyyy-mm-dd hhmm]\n" +
                "5. add deadline: deadline [description] /by [yyyy-mm-dd hhmm]\n" +
                "6. delete: delete [index]\n" +
                "7. mark done: done [index]\n" +
                "8. find: find [keyword]\n" +
                "9. say sayonara to me: bye";
    }

    /**
     * Returns a <code>String</code> message to confirm successful addition of task.
     *
     * @param tasks <code>TaskList</code> manager object of the program.
     * @param task the <code>Task</code> object to be added.
     * @return a <code>String</code> message to confirm successful addition of task.
     */
    public String printAddingMessage(TaskList tasks, Task task) {
        return "Got it dude! I've added this task:" + "\n"
                + task + "\n"
                + printTaskListSize(tasks);
    }

    public String printDoneMessage(Task task) {
        return "Got it dude! I've marked this task as done:" + "\n" + task;
    }

    /**
     * Returns a <code>String</code> message to confirm successful deletion of task.
     *
     * @param tasks <code>TaskList</code> manager object of the program.
     * @param task the <code>Task</code> object to be deleted.
     * @return a <code>String</code> message to confirm successful deletion of task.
     */
    public String printDeletingMessage(TaskList tasks, Task task) {
        return "Got it dude! I've deleted this task:" + "\n"
                + task + "\n"
                + printTaskListSize(tasks);
    }

    public String printTaskListSize(TaskList tasks) {
        int size = tasks.getListSize();
        return "Now you have " + size + " task(s) in the list.";
    }

    public String printEmptyListMessage() {
        return "Your list is currently empty dude.";
    }

    public String printTaskMessage() {
        return "Here's your list of tasks dude:";
    }

    public String printFoundTaskMessage() {
        return "Okay dude here are what I found:";
    }

    public String printNotFoundTaskMessage() {
        return "Sorry dude but I found nothing :(";
    }

    public String printTask(int i, Task task) {
        String index = String.format("%1$5d", i);
        return index + ". " + task;
    }

    public String exit() {
        return "Okay see ya dude!";
    }
}
