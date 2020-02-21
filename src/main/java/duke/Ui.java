package duke;

/**
 * Represents a user interface to interact with the user.
 */
public class Ui {

    public Ui () {}

    /**
     * Returns the greeting message when starting up the project.
     *
     * @return greeting message.
     */
    protected String greeting() {
        String str = "-----------------------------------\n"
                + "Hello! I'm CatBot\n"
                + "What can I do for you?\n"
                + "-----------------------------------\n";
        return str;
    }

    /**
     * Returns the error message when the task that user is looking for does not exist in the list.
     *
     * @return the task not exists error message.
     */
    protected String taskNotExist() {
        return "This task does not exist in your list\n";
    }

    /**
     * Returns the error message when the input of description for the new task to be created is empty.
     *
     * @return the empty description error message.
     */
    protected String emptyDescription() {
        return "☹ OOPS!!! The description of the task cannot be empty.\n";
    }

    /**
     * Returns the error message when the input of date for the new task to be created is empty.
     *
     * @return the empty date error message.
     */
    protected String emptyDate() {
        return "☹ OOPS!!! The date of the task cannot be empty.\n";
    }

    /**
     * Returns the error message when the user command input cannot be recognized.
     *
     * @return the invalid command error message.
     */
    protected String invalidCommand() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }

    /**
     * Returns the barrier message to separate each command.
     *
     * @return the barrier message.
     */
    protected String printBarrier() {
        return "-----------------------------------\n";
    }

    /**
     * Returns the goodbye message when user exit the program.
     *
     * @return the goodbye message.
     */
    protected String sayBye() {
        String str = "-----------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "-----------------------------------\n";
        return str;
    }
}

