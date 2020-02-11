package duke.parser;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.EventCommand;
import duke.command.DeadlineCommand;
import duke.command.TodoCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.exception.InvalidCommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses user input.
 */
public class Parser {
    private static final String UNKNOWN_ERROR = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String EXTRA_ERROR = "The command contains extra information!";
    private static final String INCOMPLETE_ERROR = "The command is too short and incomplete!";
    private static final String NUMBER_ERROR = "Please give the number of task!";
    private static final String DATETIME_ERROR = "Please follow this format \"31/01/2020 2300\" for date and time!";
    private static final DateTimeFormatter IN_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Parse user input into command for execution.
     *
     * @param fullCommand full user input string
     * @return the command based on the user input
     */
    public static Command parse(String fullCommand) throws InvalidCommandException {
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

        case "find":
            return prepareFind(command);

        case "bye":
            return prepareExit(command);

        default:
            throw new InvalidCommandException(UNKNOWN_ERROR);
        }
    }

    /**
     * Parses user command in the context of the find command.
     *
     * @param command full user command array
     * @return the find command
     */
    private static Command prepareFind(String[] command) throws InvalidCommandException {
        try {
            String keyword = command[1];
            return new FindCommand(keyword);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidCommandException(INCOMPLETE_ERROR);
        }
    }

    /**
     * Parses user command in the context of the exit command.
     *
     * @param command full user command array
     * @return the exit command
     */
    private static Command prepareExit(String[] command) throws InvalidCommandException {
        if (command.length > 1) {
            throw new InvalidCommandException(EXTRA_ERROR);
        }
        return new ExitCommand();
    }

    /**
     * Parses user command in the context of the event command.
     *
     * @param command full user command array
     * @return the event command
     */
    private static Command prepareEvent(String[] command) throws InvalidCommandException {
        try {
            String[] commandSplit = command[1].split(" /at ", 2);
            String desc = commandSplit[0];
            LocalDateTime dateTime = LocalDateTime.parse(commandSplit[1], IN_FORMATTER);
            return new EventCommand(desc, dateTime);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidCommandException(INCOMPLETE_ERROR);
        } catch (DateTimeParseException ex) {
            throw new InvalidCommandException(DATETIME_ERROR);
        }
    }

    /**
     * Parses user command in the context of the deadline command.
     *
     * @param command full user command array
     * @return the deadline command command
     */
    private static Command prepareDeadline(String[] command) throws InvalidCommandException {
        try {
            String[] commandSplit = command[1].split(" /by ", 2);
            String desc = commandSplit[0];
            LocalDateTime dateTime = LocalDateTime.parse(commandSplit[1], IN_FORMATTER);
            return new DeadlineCommand(desc, dateTime);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidCommandException(INCOMPLETE_ERROR);
        } catch (DateTimeParseException ex) {
            throw new InvalidCommandException(DATETIME_ERROR);
        }
    }

    /**
     * Parses user command in the context of the todo command.
     *
     * @param command full user command array
     * @return the todo command command
     */
    private static Command prepareTodo(String[] command) throws InvalidCommandException {
        try {
            String desc = command[1];
            return new TodoCommand(desc);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidCommandException(INCOMPLETE_ERROR);
        }
    }

    /**
     * Parses user command in the context of the delete command.
     *
     * @param command full user command array
     * @return the delete command command
     */
    private static Command prepareDelete(String[] command) throws InvalidCommandException {
        try {
            String[] commandSplit = command[1].split(" ");
            List<Integer> indexes = new ArrayList<>();
            for (String index : commandSplit) {
                indexes.add(Integer.parseInt(index));
            }
            return new DeleteCommand(indexes);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidCommandException(INCOMPLETE_ERROR);
        } catch (NumberFormatException ex) {
            throw new InvalidCommandException(NUMBER_ERROR);
        }
    }

    /**
     * Parses user command in the context of the done command.
     *
     * @param command full user command array
     * @return the done command command
     */
    private static Command prepareDone(String[] command) throws InvalidCommandException {
        try {
            int index = Integer.parseInt(command[1]);
            if (command.length > 2) {
                throw new InvalidCommandException(EXTRA_ERROR);
            }
            return new DoneCommand(index);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidCommandException(INCOMPLETE_ERROR);
        } catch (NumberFormatException ex) {
            throw new InvalidCommandException(NUMBER_ERROR);
        }
    }

    /**
     * Parses user command in the context of the list command.
     *
     * @param command full user command array
     * @return the list command command
     */
    private static Command prepareList(String[] command) throws InvalidCommandException {
        if (command.length > 1) {
            throw new InvalidCommandException(EXTRA_ERROR);
        }
        return new ListCommand();
    }

}
