package dude.parser;

import dude.command.AddTaskCommand;
import dude.command.ByeCommand;
import dude.command.CheckDateCommand;
import dude.command.Command;
import dude.command.DeleteCommand;
import dude.command.DoneCommand;
import dude.command.FindCommand;
import dude.command.HelpCommand;
import dude.command.ListCommand;

import dude.task.Deadline;
import dude.task.Event;
import dude.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.LinkedHashMap;
import java.util.Map;

public class Parser {
    /** Mappings from commands to their usage messages. */
    public static final Map<String, String> USAGES = new LinkedHashMap<>();

    static {
        USAGES.put("help", "help [command | -date]");
        USAGES.put("bye", "bye");
        USAGES.put("list", "list");
        USAGES.put("today", "today");
        USAGES.put("done", "done index_of_task");
        USAGES.put("delete", "delete index_of_task");
        USAGES.put("check", "check yyyy-mm-dd");
        USAGES.put("todo", "todo description");
        USAGES.put("deadline", "deadline description /by yyyy-mm-dd");
        USAGES.put("event", "event description /from yyyy-mm-dd /to yyyy-mm-dd");
        USAGES.put("find", "find word");
    }

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
        String[] cmdAndBody = msg.strip().split(WHITESPACE, 2);
        Command command;

        if (cmdAndBody.length == 1) {
            command = parseSingleWordCommand(cmdAndBody[0]);
        } else {
            assert cmdAndBody.length == 2 :
                    "If length of array from String.split with limit 2 != 1, it should be 2";
            try {
                command = parseCommandWithArguments(cmdAndBody[0], cmdAndBody[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ParsingException("Look's like your command is incomplete, mate",
                        USAGES.get(cmdAndBody[0]));
            }
        }

        if (command == null) {
            throw new ParsingException("Sorry mate, I didn't catch your drift",
                    USAGES.get("help"));
        }

        return command;
    }

    /**
     * Attempts to match the user's message with single word commands bye, list, today, help
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

        case "help":
            return new HelpCommand();

        default:
            return null;
        }
    }

    /**
     * Attempts to match the user's message with a complex command with arguments.
     * The possible commands are done, delete, check, todo, deadline, event, find and help.
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
            LocalDate checkDate = DateParser.parse(args, "check");
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

        case "help":
            return new HelpCommand(args);

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

    private static Event parseEvent(String args) throws ParsingException {
        String[] eventArgs = args.split(WHITESPACE + "/from" + WHITESPACE, 2);
        String[] eventDateStrings = eventArgs[1].split(WHITESPACE + "/to" + WHITESPACE, 2);
        LocalDate from = DateParser.parse(eventDateStrings[0], "event");
        LocalDate to = DateParser.parse(eventDateStrings[1], "event");
        return new Event(eventArgs[0], from, to, false);
    }

    private static Deadline parseDeadline(String args) throws ParsingException {
        String[] deadlineArgs = args.split(WHITESPACE + "/by" + WHITESPACE, 2);
        LocalDate by = DateParser.parse(deadlineArgs[1], "deadline");
        return new Deadline(deadlineArgs[0], by, false);
    }
}
