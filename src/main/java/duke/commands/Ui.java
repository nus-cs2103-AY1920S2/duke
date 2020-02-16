package duke.commands;

import duke.exceptions.DukeException;

/**
 *  Deals with interactions with the user.
 */
public class Ui {

    /**
     * The DUKE logo.
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
     *
     * @return Duke's introduction
     */
    public String start() {
        String output = "Hello from\n" + LOGO;
        output += "Hello! I'm Duke\nWhat can I do for you?\n";
        return output;
    }

    /**
     * Used to print out horizontal lines.
     *
     * @return outputs horizontal lines
     */
    public String horizontalLines() {
        return this.HORIZONTAL_LINES;
    }

    /**
     * Prints output in a more user friendly format.
     *
     * @param input the string to be outputted
     */
    public void dukePrint(String input) {
        System.out.print(horizontalLines());
        System.out.print(input);
        System.out.print(horizontalLines());
    }

    /**
     * Checks whether done/delete command entered by used is valid.
     *
     * @param command the line input by the user
     * @param insert the name of the command (either done or delete)
     * @param listSize the current size of Tasklist
     * @throws DukeException throws an exception if the command is invalid
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
     * Checks if the description for the task entered by the user is valid.
     *
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
     * Checks if the time for the task entered by the user is valid.
     *
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

    /**
     * Checks whether done/delete command entered by used is valid.
     *
     * @param command the line input by the user
     * @param listSize the current size of Tasklist
     * @throws DukeException throws an exception if the command is invalid
     */
    public void checkUpdate(String command, int listSize) throws DukeException {
        String[] arr = command.split(" ");
        int testIndex;
        try {
            testIndex = Integer.parseInt(command.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("The task to be updated must be specified");
        }
        if (testIndex >= listSize) {
            throw new DukeException("Task " + arr[1] + " does not exist");
        }
    }
}
