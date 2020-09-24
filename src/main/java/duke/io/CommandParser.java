package duke.io;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ByeCommand;

import duke.exception.DukeException;

public class CommandParser {
    public static final String BAD_COMMAND = "Duke does not understand that command";

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
        default:
            throw new DukeException(BAD_COMMAND);
        }

        return command;
    }

}
