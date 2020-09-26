import java.io.IOException;

/**
 * Handles the User Interface; Print out the actions taken following each user command.
 */
public class Ui {
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public String line = "_____________________________________________________\n";

    /**
     * Prints the greeting message.
     *
     */
    public String start() {
        return "Hello from\n" + logo  +
                "Hello! I'm Duke\n" + "What can I do for you?\n\n" + line;
    }


    /**
     * Prints a message to inform user that a task has been marked done.
     */
    public String done() {
        return "Nice! I've marked this task as done:\n";
    }

    /**
     * Prints a message to inform user that a task has been deleted, and current number
     * of remaining tasks.
     */
    public String delete() {
        return "Noted. I've removed this task: \n";
    }

    /**
     * Inform users that tasks in the list are to be printed.
     */
    public String list() {
        return "Here are the tasks in your list:\n";
    }

    /**
     * Prints a message to inform user that a task has been added, and current number
     * of remaining tasks.
     */
    public String add() {
        return "Got it. I've added this task: \n";
    }

    /**
     * Prints the closing message before the program ends.
     */
    public String bye() {
        return line + "Bye. Hope to see you again soon!\n\n" + line;
    }

    /**
     * Prints the current number of tasks in the list.
     *
     * @param n Number of tasks in the list.
     */
    public String currTask(int n) {
        return "Now you have " + n + " tasks in the list. \n";
    }

    /**
     * Inform users that matched tasks are to be printed
     */
    public String search() {
        return line + "Here are the matching tasks in your list:\n";
    }

    /**
     * Prints the error message.
     *
     * @param err Error message.
     */
    public String exp(String err) {
        return line + new DukeException(err).errMsg() + "\n" + line;
    }

    /**
     * Inform user that there is a loading error when accessing the data file.
     */
    public String showLoadingError() {
        return exp("Sorry, there is a loading error:(");
    }
}