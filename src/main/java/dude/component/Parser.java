package dude.component;

import dude.command.AddTaskCommand;
import dude.command.ByeCommand;
import dude.command.CheckDateCommand;
import dude.command.Command;
import dude.command.DeleteCommand;
import dude.command.DoneCommand;
import dude.command.FindCommand;
import dude.command.ListCommand;

import dude.task.Deadline;
import dude.task.Event;
import dude.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.Map;

public class Parser {
    /** Mappings from commands to their usage messages. */
    private static final Map<String, String> USAGES =
            Map.of(
                    "bye", "bye",
                    "list", "list",
                    "today", "today",
                    "done", "done index_of_task",
                    "delete", "delete index_of_task",
                    "check", "check yyyy-mm-dd",
                    "todo", "todo description",
                    "deadline", "deadline description /by yyyy-mm-dd",
                    "event", "event description /from yyyy-mm-dd /to yyyy-mm-dd",
                    "find", "find word"
            );

    /** Regex for whitespace, for greater clarity. */
    private static final String WHITESPACE = "\\s+";

    /**
     * Takes raw user input and attempts to parse it to dispatch to the relevant Command with the correct arguments.
     * Throws the custom checked exception ParsingException if user input is malformed.
     * The only valid messages are those that follow the usage messages in usages exactly.
     *
     * @param msg raw user input.
     * @return Command that the user intends to execute.
     * @throws ParsingException if message is syntactically incorrect.
     */
    public static Command parse(String msg) throws ParsingException {
        msg = msg.strip();

        Command command = parseSingleWordCommand(msg);

        if (command == null) {
            String[] cmdAndBody = msg.split(WHITESPACE, 2);
            try {
                command = parseCommandWithArguments(cmdAndBody[0], cmdAndBody[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ParsingException("Look's like your command is incomplete, mate",
                        USAGES.get(cmdAndBody[0]));
            }
        }

        if (command == null) {
            throw new ParsingException("Sorry mate, I didn't catch your drift",
                    USAGES.values().toArray(new String[0]));
        }

        return command;
    }

    /**
     * Attempts to match the user's message with single word commands bye, list, today.
     * Returns null otherwise.
     */
    private static Command parseSingleWordCommand(String command) {
        switch (command) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "today":
            return new CheckDateCommand(LocalDate.now());

        default:
            return null;
        }
    }

    /**
     * Attempts to match the user's message with a complex command with arguments.
     * The possible commands are done, delete, check, todo, deadline, event and find.
     * Returns null otherwise.
     *
     * @param command the command name
     * @param args the argument body
     * @return the parsed command or null if the command name does not match
     * @throws ParsingException if the argument bodies are incorrect
     * @throws ArrayIndexOutOfBoundsException if the incorrect argument count or format is given
     */
    private static Command parseCommandWithArguments(String command, String args) throws ParsingException {
        switch (command) {
        case "done":
            int indexDone = parseNumericalArgument(args, "done");
            return new DoneCommand(indexDone);

        case "delete":
            int indexDelete = parseNumericalArgument(args, "delete");
            return new DeleteCommand(indexDelete);

        case "check":
            LocalDate checkDate = parseDate(args, "check");
            return new CheckDateCommand(checkDate);

        case "todo":
            return new AddTaskCommand(new Todo(args, false));

        case "deadline":
            Deadline deadline = parseDeadline(args);
            return new AddTaskCommand(deadline);

        case "event":
            Event event = parseEvent(args);
            return new AddTaskCommand(event);

        case "find":
            return new FindCommand(args);

        default:
            return null;
        }
    }

    private static int parseNumericalArgument(String arg, String command) throws ParsingException {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new ParsingException("That's not a number, dude!", USAGES.get(command));
        }
    }

    private static LocalDate parseDate(String dateString, String command) throws ParsingException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new ParsingException("I don't understand this date: " + dateString, USAGES.get(command));
        }
    }

    private static Event parseEvent(String args) throws ParsingException {
        String[] eventArgs = args.split(WHITESPACE + "/from" + WHITESPACE, 2);
        String[] eventDateStrings = eventArgs[1].split(WHITESPACE + "/to" + WHITESPACE, 2);
        LocalDate from = parseDate(eventDateStrings[0], "event");
        LocalDate to = parseDate(eventDateStrings[1], "event");
        return new Event(eventArgs[0], from, to, false);
    }

    private static Deadline parseDeadline(String args) throws ParsingException {
        String[] deadlineArgs = args.split(WHITESPACE + "/by" + WHITESPACE, 2);
        LocalDate by = parseDate(deadlineArgs[1], "deadline");
        return new Deadline(deadlineArgs[0], by, false);
    }
}
