package duke.parser;

import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

import duke.exception.DukeException;
import duke.exception.MissingParsedArgumentsException;

class CommandParser extends Parser {
    static ExitCommand parseExit(String[] input)
            throws MissingParsedArgumentsException {
        if (!hasNumArguments(input, 1)) {
            throw new MissingParsedArgumentsException();
        }

        return new ExitCommand();
    }

    static DoneCommand parseDone(String[] input) throws DukeException {
        if (!hasNumArguments(input, 2)) {
            throw new MissingParsedArgumentsException();
        }

        int taskId = parseInt(input[1]);
        return new DoneCommand(taskId);
    }

    static DeleteCommand parseDelete(String[] input) throws DukeException {
        if (!hasNumArguments(input, 2)) {
            throw new MissingParsedArgumentsException();
        }

        int taskId = parseInt(input[1]);
        return new DeleteCommand(taskId);
    }

    static ListCommand parseList(String[] input) throws DukeException {
        if (!hasNumArguments(input, 1)) {
            throw new MissingParsedArgumentsException();
        }

        return new ListCommand();
    }

    static FindCommand parseFind(String[] input) throws DukeException {
        if (!hasNumArguments(input, 2)) {
            throw new MissingParsedArgumentsException();
        }

        return new FindCommand(input[1]);
    }
}
