package duke.io;

import duke.command.*;

import duke.exception.DukeException;

/**
 * This class delegates a specific command subclass based on used input by
 * instancing the correct subclass.
 */
public class CommandParser {
    public static final String BAD_COMMAND = "Duke does not understand that command";

    /**
     * Derives the correct Command object base on the description received.
     *
     * @param commandDesc describes the type of command as well as additional details it may need.
     * @return A Command object that can be run
     * @throws  DukeException unable to generate command as the description not recognized
     */
    public static Command parseCommand(String[] commandDesc) throws DukeException {
        String commandName = commandDesc[0];
        String arguments = null;
        if (commandDesc.length > 1) {
            arguments = commandDesc[1];
        }

        Command command;
        switch (commandName) {
        case "bye":
            command = new ByeCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "done":
            command = new DoneCommand(arguments);
            break;
        case "todo": // Fallthrough
        case "deadline": // Fallthrough
        case "event":
            command = new AddCommand(commandName, arguments);
            break;
        case "delete":
            command = new DeleteCommand(arguments);
            break;
        case "find":
            command = new FindCommand(arguments);
            break;
        default:
            throw new DukeException(BAD_COMMAND);
        }

        return command;
    }

}
