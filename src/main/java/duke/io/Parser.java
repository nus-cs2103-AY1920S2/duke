package duke.io;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SetPriorityCommand;
import duke.exception.DukeException;
import duke.task.Task;

public class Parser {

    /**
     * Parse a user's input into one or more supported Commands.
     *
     * @param input The input command.
     * @return A command created using the parsed inputs.
     * @throws DukeException If the command is not recognized.
     */
    public Command parse(String input) throws DukeException {
        if (input.equals("")) {
            // Assume is rogue enter input
            return null;
        }

        String[] firstSplit = input.split(" ", 2);
        String command = firstSplit[0];
        String extras = firstSplit.length > 1 ? firstSplit[1] : "";
        String[] args = extras.split(" ");

        switch (command) {
        case "bye":
            // Fallthrough
        case "quit":
            // Fallthrough
        case "exit":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand(extras);
        case "todo":
            return new AddCommand(Task.TaskType.TASK_TYPE_TODO, extras);
        case "deadline":
            return new AddCommand(Task.TaskType.TASK_TYPE_DEADLINE, extras);
        case "event":
            return new AddCommand(Task.TaskType.TASK_TYPE_EVENT, extras);
        case "delete":
            return new DeleteCommand(tryParseInt(args[0]));
        case "setpriority":
            return new SetPriorityCommand(tryParseInt(args[0]), Task.Priority.getEnumByString(args[1]));
        case "done":
            return new DoneCommand(tryParseInt(args[0]));
        default:
            throw new DukeException("'" + command + "' is not a recognized command." +
                    "\nYou are advised to stop trying to break the system.");
        }
    }

    private int tryParseInt(String toParse) {
        return tryParseInt(toParse, -1);
    }

    private int tryParseInt(String toParse, int defaultValue) {
        try {
            return Integer.parseInt(toParse);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
