package duke;

/**
 * Represents the user interface. <code>Ui</code> outputs messages and interacts with the user.
 */
public class Ui {
    public Ui() {

    }

    /**
     * Bids farewell after the user input "bye".
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Greets the user.
     */
    public String greet() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Prints the list of tasks in the order of input.
     *
     * @param taskList A list which stores the tasks input by the user.
     */
    public String list(TaskList taskList) {
        return "Here are the tasks in your list: \n" + taskList;
    }

    /**
     * Prints the list of search results.
     *
     * @param taskList List of tasks.
     */
    public String findList(TaskList taskList) {
        return "Here are the matching tasks in your list: \n" + taskList;
    }

    /**
     * Prints a message to notify the user that a task is deleted.
     *
     * @param delTask Task to be deleted.
     * @param totalTask Total number of tasks in the tasks list.
     */
    public String delete(Task delTask, int totalTask) {
        return "Noted. I've removed this task: \n" + delTask
                + "\nNow you have " + totalTask + " tasks in the list.";
    }

    /**
     * Prints a message to notify the user that a task is completed.
     *
     * @param doneTask Task that is completed
     */
    public String done(Task doneTask) {
        return "Nice! I've marked this task as done: \n" + doneTask;
    }

    /**
     * Prints a message to notify the user that a task's status has been changed to <code>incomplete</code>.
     *
     * @param doneTask Task that is completed
     */
    public String undone(Task doneTask) {
        return "The task's status has been changed to incomplete: \n" + doneTask;
    }

    /**
     * Prints a message to notify the user that a new task has been added to the list.
     *
     * @param newTask Task to be added.
     * @param totalTask Total number of tasks in the tasks list.
     */
    public String addTask(Task newTask, int totalTask) {
        return "Got it. I've added this task: \n" + newTask
                + "\nNow you have " + totalTask + " tasks in the list.";
    }

    /**
     * Prints a message to notify the user that a task is already completed.
     *
     * @param doneTask Task that is already completed
     */
    public String showAlreadyDone(Task doneTask) {
        return "You have already completed the task: \n" + doneTask;
    }

    /**
     * Prints a message to warn the user when the system is unable to recognise the command.
     */
    public String showInvalidCommand() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Prints a message to warn the user when the index input for a <code>delete</code> or <code>done</code>
     * command is invalid.
     */
    public String showInvalidTaskIndex() {
        return "OOPS!!! The task is unavailable.";
    }

    /**
     * Prints a message to warn the user when the description for a deadline is invalid.
     */
    public String showInvalidDeadlineDesc() {
        return "OOPS!!! Wrong description format for deadline.\n\n"
                + "Please enter your deadline in the format of: \n"
                + "  \"deadline (description) /by (date & time)\"\n\n"
                + "The date and time should be in the format of: \n"
                + "  \"[yyyy-MM-dd HHmm]\"";
    }

    /**
     * Prints a message to warn the user when the description for a event is invalid.
     */
    public String showInvalidEventDesc() {
        return "OOPS!!! Wrong description format for event.\n"
                + "  \"event (description) /at (date & time)\"\n\n"
                + "The date and time should be in the format of: \n"
                + "  \"[yyyy-MM-dd HHmm]\"";
    }

    /**
     * Prints a message to warn the user when the description for a todo is empty.
     */
    public String showEmptyTodoDesc() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }

    /**
     * Prints a message to warn the user when the description for a deadline is empty.
     */
    public String showEmptyDeadlineDesc() {
        return "OOPS!!! The description of a deadline cannot be empty.";
    }

    /**
     * Prints a message to warn the user when the description for a event is empty.
     */
    public String showEmptyEventDesc() {
        return "OOPS!!! The description of a event cannot be empty.";
    }

    /**
     * Prints a message to warn the user when unable to read file.
     */
    public String showInvalidFile() {
        return "OOPS!!! Unable to read the tasks in the file.";
    }

    /**
     * Prints a message when the keyword the user is finding is unavailable.
     */
    public String showTaskNotFound() {
        return "OOPS!!! The tasks you are searching for is unavailable.";
    }

    /**
     * Prints a message when the description for <code>sort</code> is invalid.
     */
    public String showInvalidSortDesc() {
        return "OOPS!!! Wrong description format for sort.\n\n"
                + "Please enter the message as the following formats: \n"
                + "  \"sort /by time\", \n"
                + "  \"sort /by task name\" or \n"
                + "  \"sort /by task type\".\n";
    }
}
