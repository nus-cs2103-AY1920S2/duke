package duke.parser;

import duke.command.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private final static String UNKNOWN_ERROR = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private final static String EXTRA_ERROR = "The command contains extra information!";
    private final static String INCOMPLETE_ERROR = "The command is too short and incomplete!";
    private final static String NUMBER_ERROR = "Please give the number of task!";
    private final static String DATETIME_ERROR = "Please follow this format \"31/01/2020 2300\" for date and time!";
    private final static DateTimeFormatter IN_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static Command parse(String fullCommand) {
        String[] command = fullCommand.split(" ", 2);
        switch (command[0]) {

        case "list":
            return prepareList(command);

        case "done":
            return prepareDone(command);

        case "delete":
            return prepareDelete(command);

        case "todo":
            return prepareTodo(command);

        case "deadline":
            return prepareDeadline(command);

        case "event":
            return prepareEvent(command);

        case "bye":
            return prepareExit(command);

        default:
            return new InvalidCommand(UNKNOWN_ERROR);
        }
    }

    private static Command prepareExit(String[] command) {
        if (command.length > 1) {
            return new InvalidCommand(EXTRA_ERROR);
        }
        return new ExitCommand();
    }

    private static Command prepareEvent(String[] command) {
        try {
            String[] commandSplit = command[1].split(" /at ", 2);
            String desc = commandSplit[0];
            LocalDateTime dateTime = LocalDateTime.parse(commandSplit[1], IN_FORMATTER);
            return new EventCommand(desc, dateTime);
        } catch (IndexOutOfBoundsException ex) {
            return new InvalidCommand(INCOMPLETE_ERROR);
        } catch (DateTimeParseException ex) {
            return new InvalidCommand(DATETIME_ERROR);
        }
    }

    private static Command prepareDeadline(String[] command) {
        try {
            String[] commandSplit = command[1].split(" /by ", 2);
            String desc = commandSplit[0];
            LocalDateTime dateTime = LocalDateTime.parse(commandSplit[1], IN_FORMATTER);
            return new DeadlineCommand(desc, dateTime);
        } catch (IndexOutOfBoundsException ex) {
            return new InvalidCommand(INCOMPLETE_ERROR);
        } catch (DateTimeParseException ex) {
            return new InvalidCommand(DATETIME_ERROR);
        }
    }

    private static Command prepareTodo(String[] command) {
        try {
            String desc = command[1];
            return new TodoCommand(desc);
        } catch (IndexOutOfBoundsException ex) {
            return new InvalidCommand(INCOMPLETE_ERROR);
        }
    }

    private static Command prepareDelete(String[] command) {
        try {
            int index = Integer.parseInt(command[1]);
            if (command.length > 2) {
                return new InvalidCommand(EXTRA_ERROR);
            }
            return new DeleteCommand(index);
        } catch (IndexOutOfBoundsException ex) {
            return new InvalidCommand(INCOMPLETE_ERROR);
        } catch (NumberFormatException ex) {
            return new InvalidCommand(NUMBER_ERROR);
        }
    }

    private static Command prepareDone(String[] command) {
        try {
            int index = Integer.parseInt(command[1]);
            if (command.length > 2) {
                return new InvalidCommand(EXTRA_ERROR);
            }
            return new DoneCommand(index);
        } catch (IndexOutOfBoundsException ex) {
            return new InvalidCommand(INCOMPLETE_ERROR);
        } catch (NumberFormatException ex) {
            return new InvalidCommand(NUMBER_ERROR);
        }
    }

    private static Command prepareList(String[] command) {
        if (command.length > 1) {
            return new InvalidCommand(EXTRA_ERROR);
        }
        return new ListCommand();
    }

}
