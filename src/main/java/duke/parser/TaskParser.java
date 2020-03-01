package duke.parser;

import duke.command.AddCommand;

import duke.parser.exception.DateFormatException;
import duke.parser.exception.MissingParserArgumentsException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

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

        Task task = new Deadline(args[0], StringParser.parseDate(args[1]));
        return new AddCommand(task);
    }

    static AddCommand parseEvent(String[] input) throws MissingParserArgumentsException {
        if (!hasNumArguments(input, 2)) {
            throw new MissingParserArgumentsException();
        }

        String[] args = input[1].split("\\s+/at\\s+");

        if (!hasNumArguments(args, 2)) {
            throw new MissingParserArgumentsException();
        }

        int timeSlotIndex = args[1].lastIndexOf(" ");

        if (timeSlotIndex <= 0) {
            // Command is incorrectly typed
            throw new MissingParserArgumentsException();
        }

        Task task = new Event(args[0],
                args[1].substring(0, timeSlotIndex).strip(),
                args[1].substring(timeSlotIndex + 1));

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
