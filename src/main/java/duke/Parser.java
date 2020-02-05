package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.common.ErrorMessage;
import duke.task.TaskType;

public class Parser {

    /**
     * Parses a string into LocalDateTime object.
     * @param datetime The string to be parsed.
     * @return A LocalDateTime object.
     * @throws DukeException Error when parsing the date.
     */
    static LocalDateTime parseDate(String datetime) throws DukeException {
        try {
            return LocalDateTime.parse(datetime.replace(' ', 'T'));
        } catch (DateTimeParseException e) {
            throw new DukeException("Date should be in the format of yyyy-MM-dd HH:mm");
        }
    }

    /**
     * Parses some details to obtain an AddCommand object of todo type.
     * @param details The details of the command.
     * @return An AddCommand object with the details.
     * @throws DukeException Error when parsing the todo.
     */
    static Command parseTodo(String details) throws DukeException {
        if (details.length() <= 0) {
            throw new DukeException(ErrorMessage.EMPTY_DESCRIPTION);
        }

        return new AddCommand(TaskType.TODO, details);
    }

    /**
     * Parses some details to obtain an AddCommand object of deadline type.
     * @param details The details of the command.
     * @return An AddCommand object with the details.
     * @throws DukeException Error when parsing the deadline.
     */
    static Command parseDeadline(String details) throws DukeException {
        if (details.length() == 0) {
            throw new DukeException(ErrorMessage.EMPTY_DESCRIPTION);
        }

        int keyPosition = details.indexOf(" /by ");

        if (keyPosition == -1) {
            throw new DukeException("DEADLINE requires a format of "
                    + "<description> /by <deadline>.");
        }

        String description = details.substring(0, keyPosition);
        if (description.length() < 1) {
            throw new DukeException(ErrorMessage.EMPTY_DESCRIPTION);
        }

        LocalDateTime deadline = parseDate(details.substring(keyPosition + 5));

        HashMap<String, Object> values = new HashMap<>();
        values.put("deadline", deadline);

        return new AddCommand(TaskType.DEADLINE, description, values);
    }

    /**
     * Parses some details to obtain an AddCommand object of event type.
     * @param details The details of the command.
     * @return An AddCommand object with the details.
     * @throws DukeException Error when parsing the event.
     */
    static Command parseEvent(String details) throws DukeException {
        if (details.length() == 0) {
            throw new DukeException(ErrorMessage.EMPTY_DESCRIPTION);
        }

        int keyPosition = details.indexOf(" /at ");

        if (keyPosition == -1) {
            throw new DukeException("EVENT requires a format of "
                    + "<description> /at <datetime>.");
        }

        String description = details.substring(0, keyPosition);
        if (description.length() < 1) {
            throw new DukeException(ErrorMessage.EMPTY_DATETIME);
        }

        LocalDateTime datetime = parseDate(details.substring(keyPosition + 5));

        HashMap<String, Object> values = new HashMap<>();
        values.put("datetime", datetime);

        return new AddCommand(TaskType.DEADLINE, description, values);
    }

    /**
     * Parses some details to obtain a MarkCommand object.
     * @param details The details of the command.
     * @return A MarkCommand with an index in the list to be marked.
     * @throws DukeException Error when parsing the done command.
     */
    static Command parseDone(String details) throws DukeException {
        int index;

        if (details.length() == 0) {
            throw new DukeException(ErrorMessage.EMPTY_INDEX);
        }
        
        try {
            index = Integer.parseInt(details);
        } catch (NumberFormatException e) {
            throw new DukeException(ErrorMessage.INVALID_INDEX);
        }

        return new MarkCommand(index);
    }

    /**
     * Parses some details to obtain a DeleteCommand object.
     * @param details The details of the command.
     * @return A DeleteCommand with an index in the list to be deleted.
     * @throws DukeException Error when parsing the delete command.
     */
    static Command parseDelete(String details) throws DukeException {
        int index;

        if (details.length() == 0) {
            throw new DukeException(ErrorMessage.EMPTY_INDEX);
        }
        
        try {
            index = Integer.parseInt(details);
        } catch (NumberFormatException e) {
            throw new DukeException(ErrorMessage.INVALID_INDEX);
        }

        return new DeleteCommand(index);
    }

    /**
     * Parses the user command to return the appropriate Command object.
     * @param userCommand The raw command from the user.
     * @return A Command object which represents the user's command.
     * @throws DukeException Error when parsing the command.
     */
    public static Command parse(String userCommand) throws DukeException {

        String instruction = "";
        String details = "";
        int splitIndex = userCommand.indexOf(' ');

        if (userCommand.length() == 0) {
            throw new DukeException(ErrorMessage.EMPTY_COMMAND);
        }

        if (splitIndex < 0) {
            instruction = userCommand.toUpperCase();
        } else {
            instruction = userCommand.substring(0, splitIndex).toUpperCase();
            details = userCommand.substring(splitIndex + 1);
        }

        switch (instruction) {
        case "TODO":
            return parseTodo(details);
        case "DEADLINE":
            return parseDeadline(details);
        case "EVENT":
            return parseEvent(details);
        case "LIST":
            return new ListCommand();
        case "DONE":
            return parseDone(details);
        case "DELETE":
            return parseDelete(details);
        case "BYE":
            return new ExitCommand();
        default:
            throw new DukeException(ErrorMessage.COMMAND_NOT_FOUND);
        }
    }
}