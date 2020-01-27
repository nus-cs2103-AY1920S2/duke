package dude.component;

import dude.task.Deadline;
import dude.task.Event;
import dude.task.Todo;

import dude.command.AddTaskCommand;
import dude.command.ByeCommand;
import dude.command.CheckDateCommand;
import dude.command.Command;
import dude.command.DeleteCommand;
import dude.command.DoneCommand;
import dude.command.ListCommand;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.Map;

public class Parser {
    private static Map<String, String> usages =
            Map.of(
                    "bye"     , "bye",
                    "list"    , "list",
                    "today"   , "today",
                    "done"    , "done index_of_task",
                    "delete"  , "delete index_of_task",
                    "check"   , "check yyyy-mm-dd",
                    "todo"    , "todo description",
                    "deadline", "deadline description /by yyyy-mm-dd",
                    "event"   , "event description /from yyyy-mm-dd /to yyyy-mm-dd"
            );

    // Regex for whitespace, for greater clarity
    private final static String whitespace = "\\s+";

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
        }

        String[] cmdAndBody = msg.split(whitespace, 2);

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
                String[] deadlineArgs = cmdAndBody[1].split(whitespace + "/by" + whitespace, 2);
                LocalDate by = parseDate(deadlineArgs[1], "deadline");
                return new AddTaskCommand(new Deadline(deadlineArgs[0], by, false));

            case "event":
                String[] eventArgs = cmdAndBody[1].split(whitespace + "/from" + whitespace, 2);
                String[] eventDateStrings = eventArgs[1].split(whitespace + "/to" + whitespace, 2);
                LocalDate from = parseDate(eventDateStrings[0], "event");
                LocalDate to = parseDate(eventDateStrings[1], "event");
                return new AddTaskCommand(new Event(eventArgs[0], from, to, false));

            default:
                throw new ParsingException("Sorry mate, I didn't catch your drift",
                        usages.values().toArray(new String[usages.size()]));

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParsingException("Look's like your command is incomplete, mate",
                    usages.get(cmdAndBody[0]));
        }

    }

    private static int parseNumericalArgument(String arg, String command) throws ParsingException {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new ParsingException("That's not a number, dude!", usages.get(command));
        }
    }

    private static LocalDate parseDate(String dateString, String command) throws ParsingException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new ParsingException("I don't understand this date: " + dateString, usages.get(command));
        }
    }


}
