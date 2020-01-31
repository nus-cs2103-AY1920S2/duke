package duke.parser;

import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.AddCommand;
import duke.exception.DukeException;

public class Parser {

    /**
     * Parse the command entered by user into a Command object if command exists.
     *
     * @param  command  the input by the user
     * @return  the parsed command
     * @throws  DukeException   if command doesn't exist
     */
    public static Command parse(String command) throws DukeException {
        String[] commandTokens = command.split(" ");
        switch (commandTokens[0]) {
        case "bye":
            return new ExitCommand(command);
        case "list":
            return new ListCommand(command);
        case "done":
            return new DoneCommand(command);
        case "delete":
            return new DeleteCommand(command);
        case "find":
            return new FindCommand(command);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(command);
        default:
            throw new DukeException("OOPS!!! Cannot parse command!");
        }
    }
}
