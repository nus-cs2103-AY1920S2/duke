package app.core.commands;

import app.util.Parser;
import app.util.StringPair;

import app.exceptions.BaseException;
import app.exceptions.WrongCommandException;

public class CommandManager {
    public Command getCommand(String userInput) throws BaseException {
        StringPair tokens = Parser.parse(userInput);
        String command = tokens.getFirstValue();
        String args = tokens.getSecondValue();

        switch (command) {
        case "bye":
            return new ByeCommand();
        case "todo":
            return new TodoCommand(args);
        case "event":
            return new EventCommand(args);
        case "deadline":
            return new DeadlineCommand(args);
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand(args);
        case "done":
            return new DoneCommand(args);
        case "delete":
            return new DeleteCommand(args);
        default:
            throw new WrongCommandException(String.format("The command '%s' is not supported", command));
        }
    }
}