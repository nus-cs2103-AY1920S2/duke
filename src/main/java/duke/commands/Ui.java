package duke.commands;

import duke.exceptions.DukeException;

/**
 *  deals with interactions with the user.
 */
public class Ui {

    /**
     * the DUKE logo.
     */
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    /**
     * lines to separate user input and programme output.
     */
    private static final String HORIZONTAL_LINES = "__________________________"
            + "____________________________________________________________\n";

    /**
     * Says hi to the user and includes the logo.
     */
    public String start() {
        String output = "Hello from\n" + LOGO;
        output += "Hello! I'm Duke\nWhat can I do for you?\n";
        return output;
    }

    /**
     * checks whether done/delete command entered by used is valid.
     * @param command the line input by the user
     * @param insert the name of the command (either done or delete)
     * @param listSize the current size of Tasklist
     * @throws DukeException throws an exception is the command is invalid
     */
    public void checkCommand(String command, String insert,
                             int listSize) throws DukeException {
        String[] arr = command.split(" ");
        if (arr.length < 2) {
            throw new DukeException("The task to be marked as " + insert
                    + " must be specified");
        }
        if (Integer.valueOf(arr[1]) - 1 >= listSize) {
            throw new DukeException("Task " + arr[1] + " does not exist");
        }
    }

    /**
     * checks if the description for the task entered by the user is valid.
     * @param description the input
     * @param insert the type of task (either todo, deadline or event)
     * @throws DukeException throws an exception is the command is invalid
     */
    public void checkDescription(String[] description, String insert)
            throws DukeException {
        if (description.length < 2) {
            throw new DukeException("The description of a " + insert
                    + " cannot be empty.");
        }
    }

    /**
     * checks if the time for the task entered by the user is valid.
     * @param arr the input
     * @param insert the type of task (either deadline or event)
     * @throws DukeException throws an exception is the command is invalid
     */
    public void checkTime(String[] arr, String insert)
            throws DukeException {
        if (arr.length < 2) {
            throw new DukeException("The time of a " + insert
                    + " cannot be empty.");
        }
        String[] time = (arr[1].split(" ", 2));
        if (time.length < 2) {
            throw new DukeException("The time of a " + insert
                    + " cannot be empty.");
        }
    }
}
