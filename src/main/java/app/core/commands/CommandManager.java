package app.core.commands;

import app.core.Messages;

import app.util.Parser;
import app.util.StringPair;

import app.exceptions.BaseException;
import app.exceptions.InvalidCommandException;

/**
 * This class contains all the data and functions related to Commands.
 */
public final class CommandManager {
    /**
     * Parses a user input and returns the corresponding command.
     * @param userInput User Input
     * @return A Command object that can be executed
     * @throws BaseException If any error occurs during the parsing 
     *     of the user input
     */
    public Command getCommand(String userInput) throws BaseException {
        StringPair tokens = Parser.parse(userInput);
        String command = tokens.getFirstValue();
        String args = tokens.getSecondValue();
        
        assert command != null : "Token's command should not be null";
        assert args != null : "Token's args should not be null";

        switch (command) {
        case "bye":
            return new ByeCommand(args);
        case "todo":
            return new TodoCommand(args);
        case "event":
            return new EventCommand(args);
        case "deadline":
            return new DeadlineCommand(args);
        case "list":
            return new ListCommand(args);
        case "find":
            return new FindCommand(args);
        case "done":
            return new DoneCommand(args);
        case "delete":
            return new DeleteCommand(args);
        case "":
            throw new InvalidCommandException(Messages.EMPTY_COMMAND_MESSAGE);
        default:
            throw new InvalidCommandException(String.format(Messages.UNSUPPORTED_COMMAND_MESSAGE, command));
        }
    }
}