package duke;

/**
 * Helps to parse and handle raw user commands from the user to commands 'understandable' by the rest of Duke.
 */
public class Parser {

    /**
     * Creates a new Parser object.
     */
    public Parser() {
    }

    /**
     * Determines if a string can be split (into 2 parts), based on a certain delimiter.
     *
     * @param str The string which is to be split.
     * @param regex The delimiter or regex to split the string by.
     * @return True if string can be split, false otherwise.
     */
    private boolean canSplitStr(String str, String regex) {
        String[] strArr = str.split(regex);

        return (strArr.length == 2);
    }

    /**
     * Checks if the integer ID for accessing the Task in the task-list is valid.
     *
     * @param strId Integer ID in String format.
     * @param tasks The task-list.
     * @return True if the string represents a valid ID, false otherwise.
     */
    private boolean isValidId(String strId, TaskList tasks) {
        int id;
        try {
            id = Integer.parseInt(strId);
        } catch (NumberFormatException e) {
            return false;
        }

        return (!((id - 1) > tasks.getNumTasks() - 1 || (id - 1) < 0));
    }

    /**
     * Parses raw commands from user input.
     *
     * @param cmd The raw command entered by user.
     * @param tasks The current task-list.
     * @return Command object representing the parsed user command.
     * @throws DukeException If the command is invalid or if there were errors parsing the command.
     */
    public Command parse(String cmd, TaskList tasks) throws DukeException {
        String[] strArr;
        String str1;
        Task t;
        if (cmd.equals(CommandType.LIST_CMD.toString())) {
            return new Command(CommandType.LIST_CMD, null);
        } else if (cmd.startsWith(CommandType.DONE_CMD.toString())) {
            if (!canSplitStr(cmd, "done\\s+")) {
                throw new DukeException("The task to mark done cannot be empty");
            }

            str1 = cmd.split("done\\s+")[1];

            if (!isValidId(str1, tasks)) {
                throw new DukeException("The task to mark done is not in the list");
            }

            return new Command(CommandType.DONE_CMD, new String[] { str1 });
        } else if (cmd.startsWith(CommandType.DELETE_CMD.toString())) {
            if (!canSplitStr(cmd, "delete\\s+")) {
                throw new DukeException("The task to delete cannot be empty");
            }

            str1 = cmd.split("delete\\s+")[1];

            if (!isValidId(str1, tasks)) {
                throw new DukeException("The task to mark delete is not in the list");
            }

            return new Command(CommandType.DELETE_CMD, new String[] { str1 });
        } else if (cmd.startsWith(CommandType.TODO_CMD.toString())) {
            if (!canSplitStr(cmd, "todo\\s+")) {
                throw new DukeException("The description of a todo cannot be empty");
            }

            str1  = cmd.split("todo\\s+")[1];

            return new Command(CommandType.TODO_CMD, new String[] { str1 });
        } else if (cmd.startsWith(CommandType.DEADLINE_CMD.toString())) {
            if (!canSplitStr(cmd, "deadline\\s+")) {
                throw new DukeException("The description and timing of a deadline cannot be empty");
            }

            str1 = cmd.split("deadline\\s+")[1];

            if (!canSplitStr(str1, "\\s+/by\\s+")) {
                throw new DukeException("Both the description and timing of a deadline cannot be empty");
            }

            strArr = str1.split("\\s+/by\\s+");

            String name = strArr[0];
            String by = strArr[1];

            return new Command(CommandType.DEADLINE_CMD, new String[] { name, by });
        } else if (cmd.startsWith(CommandType.EVENT_CMD.toString())) {
            if (!canSplitStr(cmd, "event\\s+")) {
                throw new DukeException("The description and timing of an event cannot be empty");
            }

            str1 = cmd.split("event\\s+")[1];

            if (!canSplitStr(str1, "\\s+/at\\s+")) {
                throw new DukeException("Both the description and timing of an event cannot be empty");
            }

            strArr = str1.split("\\s+/at\\s+");

            String name = strArr[0];
            String at = strArr[1];

            return new Command(CommandType.EVENT_CMD, new String[] { name, at });
        } else if (cmd.startsWith(CommandType.FIND_CMD.toString())) {
            if (!canSplitStr(cmd, "find\\s+")) {
                throw new DukeException("The description of a todo cannot be empty");
            }

            str1  = cmd.split("find\\s+")[1];

            return new Command(CommandType.FIND_CMD, new String[] { str1 });
        } else if (cmd.equals(CommandType.HELP_CMD.toString())) {
            return new Command(CommandType.HELP_CMD, null);
        } else if (cmd.equals(CommandType.BYE_CMD.toString())) {
            return new Command(CommandType.BYE_CMD, null);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
