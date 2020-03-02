package duke.parser;

import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.parser.exception.MissingParserArgumentsException;
import duke.parser.exception.ParseException;

/**
 * Represents a Parser that parses user input into Duke commands.
 */
class CommandParser extends Parser {
    static ExitCommand parseExit(String[] input)
            throws MissingParserArgumentsException {
        if (!hasNumArguments(input, 1)) {
            throw new MissingParserArgumentsException();
        }

        return new ExitCommand();
    }

    static DoneCommand parseDone(String[] input) throws ParseException {
        if (!hasNumArguments(input, 2)) {
            throw new MissingParserArgumentsException();
        }

        int taskId = StringParser.parseInt(input[1]);
        return new DoneCommand(taskId);
    }

    static DeleteCommand parseDelete(String[] input) throws ParseException {
        if (!hasNumArguments(input, 2)) {
            throw new MissingParserArgumentsException();
        }

        int taskId = StringParser.parseInt(input[1]);
        return new DeleteCommand(taskId);
    }

    static ListCommand parseList(String[] input) throws ParseException {
        if (!hasNumArguments(input, 1)) {
            throw new MissingParserArgumentsException();
        }

        return new ListCommand();
    }

    static FindCommand parseFind(String[] input) throws ParseException {
        if (!hasNumArguments(input, 2)) {
            throw new MissingParserArgumentsException();
        }

        return new FindCommand(input[1]);
    }
}
