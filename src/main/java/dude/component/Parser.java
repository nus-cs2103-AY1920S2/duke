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

        // First match against simple (single word) commands
        switch (msg) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "today":
            return new CheckDateCommand(LocalDate.now());

        default:
            break;
        }

        String[] cmdAndBody = msg.split(WHITESPACE, 2);

        // Else command is complex, then first match command name then parse arguments
        try {
            switch (cmdAndBody[0]) {
            case "done":
                int indexDone = parseNumericalArgument(cmdAndBody[1], "done");
                return new DoneCommand(indexDone);

            case "delete":
                int indexDelete = parseNumericalArgument(cmdAndBody[1], "delete");
                return new DeleteCommand(indexDelete);

            case "check":
                LocalDate checkDate = parseDate(cmdAndBody[1], "check");
                return new CheckDateCommand(checkDate);

            case "todo":
                return new AddTaskCommand(new Todo(cmdAndBody[1], false));

            case "deadline":
                String[] deadlineArgs = cmdAndBody[1].split(WHITESPACE + "/by" + WHITESPACE, 2);
                LocalDate by = parseDate(deadlineArgs[1], "deadline");
                return new AddTaskCommand(new Deadline(deadlineArgs[0], by, false));

            case "event":
                String[] eventArgs = cmdAndBody[1].split(WHITESPACE + "/from" + WHITESPACE, 2);
                String[] eventDateStrings = eventArgs[1].split(WHITESPACE + "/to" + WHITESPACE, 2);
                LocalDate from = parseDate(eventDateStrings[0], "event");
                LocalDate to = parseDate(eventDateStrings[1], "event");
                return new AddTaskCommand(new Event(eventArgs[0], from, to, false));

            case "find":
                return new FindCommand(cmdAndBody[1]);

            default:
                throw new ParsingException("Sorry mate, I didn't catch your drift",
                        USAGES.values().toArray(new String[USAGES.size()]));

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParsingException("Look's like your command is incomplete, mate",
                    USAGES.get(cmdAndBody[0]));
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


}
