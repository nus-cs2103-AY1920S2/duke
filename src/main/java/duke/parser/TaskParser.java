package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.command.AddCommand;
import duke.parser.exception.DateFormatException;
import duke.parser.exception.MissingParserArgumentsException;
import duke.parser.exception.TimeFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a Parser that parses user input into Duke tasks.
 */
class TaskParser extends Parser {
    static AddCommand parseDeadline(String[] input) throws MissingParserArgumentsException,
            DateFormatException {
        if (!hasNumArguments(input, 2)) {
            throw new MissingParserArgumentsException();
        }

        String[] args = input[1].split("\\s+/by\\s+");

        if (!hasNumArguments(args, 2)) {
            throw new MissingParserArgumentsException();
        }

        String description = args[0];
        LocalDate date = StringParser.parseDate(args[1]);

        Task task = new Deadline(description, date);
        return new AddCommand(task);
    }

    static AddCommand parseEvent(String[] input) throws MissingParserArgumentsException,
            DateFormatException, TimeFormatException {

        if (!hasNumArguments(input, 2)) {
            throw new MissingParserArgumentsException();
        }

        String[] args = input[1].split("\\s+/at\\s+");
        if (!hasNumArguments(args, 2)) {
            throw new MissingParserArgumentsException();
        }

        final String description = args[0];

        args = args[1].split("\\s+");
        if (!hasNumArguments(args, 2)) {
            throw new MissingParserArgumentsException();
        }

        final LocalDate date = StringParser.parseDate(args[0]);

        args = args[1].split("-");
        if (!hasNumArguments(args, 2)) {
            throw new MissingParserArgumentsException();
        }

        LocalTime startTime = StringParser.parseTime(args[0]);
        LocalTime endTime = StringParser.parseTime(args[1]);

        Task task = new Event(description, date, startTime, endTime);

        return new AddCommand(task);
    }

    static AddCommand parseTodo(String[] input) throws MissingParserArgumentsException {
        if (!hasNumArguments(input, 2)) {
            throw new MissingParserArgumentsException();
        }

        Task task = new Todo(input[1]);
        return new AddCommand(task);
    }
}
