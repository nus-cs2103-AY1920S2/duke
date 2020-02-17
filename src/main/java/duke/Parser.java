package duke;

import duke.common.command.Command;
import duke.common.command.ExitCommand;
import duke.common.message.ErrorMessage;
import duke.expense.command.AddExpenseCommand;
import duke.expense.command.DeleteExpenseCommand;
import duke.expense.command.ListExpenseCommand;
import duke.task.TaskType;
import duke.task.command.AddTaskCommand;
import duke.task.command.DeleteTaskCommand;
import duke.task.command.FindTaskCommand;
import duke.task.command.ListTaskCommand;
import duke.task.command.MarkTaskCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class Parser {

    /**
     * Parses a string into LocalDateTime object.
     * @param datetime The string to be parsed.
     * @return A LocalDateTime object.
     * @throws DukeException Error when parsing the date.
     */
    static LocalDateTime parseDateTime(String datetime) throws DukeException {
        try {
            return LocalDateTime.parse(datetime.replace(' ', 'T'));
        } catch (DateTimeParseException e) {
            throw new DukeException("Date should be in the format of "
                    + "yyyy-MM-dd HH:mm");
        }
    }

    static LocalDate parseDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date should be in the format of "
                    + "yyyy-MM-dd");
        }
    }

    static Command parseList(String details) throws DukeException {
        switch (details.toUpperCase()) {
        case "TASKS":
            return new ListTaskCommand();
        case "EXPENSES":
            return new ListExpenseCommand();
        default:
            throw new DukeException("'LIST tasks' or 'LIST expenses'");
        }
    }

    static Command parseExpense(String details) throws DukeException {
        if (details.length() <= 0) {
            throw new DukeException(ErrorMessage.EMPTY_DESCRIPTION);
        }

        int keyPosition = details.indexOf(" /dollars ");
        if (keyPosition == -1) {
            throw new DukeException("EXPENSE requires a format of "
                    + "<expense> /dollars <description> /on <date>.");
        }

        double expense = 0;
        try {
            expense = Double.parseDouble(details.substring(0, keyPosition));
        } catch (Exception e) {
            throw new DukeException("An integer amount for expense is required.");
        }

        details = details.substring(keyPosition + 10);

        keyPosition = details.indexOf(" /on ");
        if (keyPosition == -1) {
            throw new DukeException("EXPENSE requires a format of "
                    + "<expense> /dollars <description> /on <date>.");
        }

        String description = details.substring(0, keyPosition);
        if (description.length() < 1) {
            throw new DukeException(ErrorMessage.EMPTY_DESCRIPTION);
        }

        LocalDate date = parseDate(details.substring(keyPosition + 5));

        return new AddExpenseCommand(description, expense, date);
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

        return new AddTaskCommand(TaskType.TODO, details);
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

        LocalDateTime datetime = parseDateTime(details.substring(keyPosition + 5));

        HashMap<String, Object> values = new HashMap<>();
        values.put("datetime", datetime);

        return new AddTaskCommand(TaskType.DEADLINE, description, values);
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

        LocalDateTime datetime = parseDateTime(details.substring(keyPosition + 5));

        HashMap<String, Object> values = new HashMap<>();
        values.put("datetime", datetime);

        return new AddTaskCommand(TaskType.DEADLINE, description, values);
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

        return new MarkTaskCommand(index);
    }

    /**
     * Parses the find command with its details.
     * @param details The search string of the find command.
     * @return The FindCommand object with its search string.
     * @throws DukeException Error when parsing the command.
     */
    static Command parseFind(String details) throws DukeException {
        if (details.length() == 0) {
            throw new DukeException(ErrorMessage.EMPTY_SEARCH);
        }

        return new FindTaskCommand(details);
    }

    /**
     * Parses some details to obtain a DeleteCommand object.
     * @param details The details of the command.
     * @return A DeleteCommand with an index in the list to be deleted.
     * @throws DukeException Error when parsing the delete command.
     */
    static Command parseDelete(String details) throws DukeException {
        int splitIndex = details.indexOf(' ');

        if (splitIndex < 0) {
            throw new DukeException("'DELETE task <index>' or 'DELETE expense <index>'");
        }

        String listType = details.substring(0, splitIndex).toUpperCase();
        if (!listType.equals("TASK") && !listType.equals("EXPENSE")) {
            throw new DukeException("'DELETE task <index>' or 'DELETE expense <index>'");
        }

        String indexDetails = details.substring(splitIndex + 1);
        int index;

        if (indexDetails.length() == 0) {
            throw new DukeException(ErrorMessage.EMPTY_INDEX);
        }
        
        try {
            index = Integer.parseInt(indexDetails);
        } catch (NumberFormatException e) {
            throw new DukeException(ErrorMessage.INVALID_INDEX);
        }

        switch (listType) {
        case "TASK":
            return new DeleteTaskCommand(index);
        case "EXPENSE":
            return new DeleteExpenseCommand(index);
        default:
            assert false : "listType should be in the cases";
            throw new DukeException(ErrorMessage.getRandomCommandErrorMessage());
        }
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
        case "EXPENSE":
            return parseExpense(details);
        case "LIST":
            return parseList(details);
        case "EXPENSES": // continue
        case "TASKS":
            return parseList(instruction);
        case "DONE":
            return parseDone(details);
        case "FIND":
            return parseFind(details);
        case "DELETE":
            return parseDelete(details);
        case "BYE":
            return new ExitCommand();
        default:
            throw new DukeException(ErrorMessage.getRandomCommandErrorMessage());
        }
    }
}