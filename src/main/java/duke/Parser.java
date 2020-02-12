package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.GetCommand;
import duke.command.ListCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeFormatException;
import duke.exception.InvalidIdException;
import duke.exception.MissingArgumentException;
import duke.exception.UnknownCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Makes sense of the user command.
 */
public class Parser {
    /**
     * Parses the specified command and returns the corresponding Command object.
     *
     * @param command The command to be parsed.
     * @return The corresponding Command object.
     * @throws DukeException If the command is invalid.
     */
    public static Command parse(String command) throws DukeException {
        if (command.trim().equals("bye")) {
            return new ExitCommand();
        }
        if (command.trim().equals("list")) {
            return new ListCommand();
        }
        Command cmd;
        String[] commandArray = command.split(" ", 2);
        switch (commandArray[0]) {
        case "todo":
            cmd = createTodoCommand(commandArray);
            break;
        case "deadline":
            cmd = createDeadlineCommand(commandArray);
            break;
        case "event":
            cmd = createEventCommand(commandArray);
            break;
        case "done":
            cmd = createDoneCommand(commandArray);
            break;
        case "update":
            cmd = createUpdateCommand(commandArray);
            break;
        case "delete":
            cmd = createDeleteCommand(commandArray);
            break;
        case "get":
            cmd = createGetCommand(commandArray);
            break;
        case "find":
            cmd = createFindCommand(commandArray);
            break;
        default:
            throw new UnknownCommandException();
        }
        return cmd;
    }

    /**
     * Returns an AddCommand for the todo.
     * @param commandArray The array containing the input string.
     * @return An AddCommand for the todo.
     * @throws DukeException If the commandArray is invalid.
     */
    private static Command createTodoCommand(String[] commandArray) throws DukeException {
        try {
            return new AddCommand(commandArray[0], commandArray[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException("description of a todo");
        }
    }

    /**
     * Returns an AddCommand for the deadline.
     * @param commandArray The array containing the input string.
     * @return An AddCommand for the deadline.
     * @throws DukeException If the commandArray is invalid.
     */
    private static Command createDeadlineCommand(String[] commandArray) throws DukeException {
        try {
            return new AddCommand(commandArray[0], commandArray[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException("description and due date of a deadline");
        }
    }

    /**
     * Returns an AddCommand for the event.
     * @param commandArray The array containing the input string.
     * @return An AddCommand for the event.
     * @throws DukeException If the commandArray is invalid.
     */
    private static Command createEventCommand(String[] commandArray) throws DukeException {
        try {
            return new AddCommand(commandArray[0], commandArray[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException("description and date and time of an event");
        }
    }

    /**
     * Returns a DoneCommand.
     * @param commandArray The array containing the input string.
     * @return A DoneCommand.
     * @throws DukeException If the commandArray is invalid.
     */
    private static Command createDoneCommand(String[] commandArray) throws DukeException {
        try {
            if (commandArray[1].trim().equals("")) {
                throw new MissingArgumentException("ID of the task done");
            }
            return new DoneCommand(Integer.parseInt(commandArray[1].trim()));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException("ID of the task done");
        } catch (NumberFormatException e) {
            throw new InvalidIdException("done");
        }
    }

    /**
     * Returns an UpdateCommand.
     * @param commandArray The array containing the input string.
     * @return An UpdateCommand.
     * @throws DukeException If the commandArray is invalid.
     */
    private static Command createUpdateCommand(String[] commandArray) throws DukeException {
        try {
            if (commandArray[1].trim().equals("")) {
                throw new MissingArgumentException("ID and details of the task to update");
            }
            String[] splitArray = commandArray[1].trim().split(" ", 2);
            return new UpdateCommand(Integer.parseInt(splitArray[0].trim()), splitArray[1].trim());
        }  catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException("ID and details of the task to update");
        } catch (NumberFormatException e) {
            throw new InvalidIdException("to update");
        }
    }

    /**
     * Returns a DeleteCommand.
     * @param commandArray The array containing the input string.
     * @return A DeleteCommand.
     * @throws DukeException If the commandArray is invalid.
     */
    private static Command createDeleteCommand(String[] commandArray) throws DukeException {
        try {
            if (commandArray[1].trim().equals("")) {
                throw new MissingArgumentException("ID of the task to delete");
            }
            return new DeleteCommand(Integer.parseInt(commandArray[1].trim()));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException("ID of the task to delete");
        } catch (NumberFormatException e) {
            throw new InvalidIdException("to delete");
        }
    }

    /**
     * Returns a GetCommand.
     * @param commandArray The array containing the input string.
     * @return A GetCommand.
     * @throws DukeException If the commandArray is invalid.
     */
    private static Command createGetCommand(String[] commandArray) throws DukeException {
        try {
            if (commandArray[1].trim().equals("")) {
                throw new MissingArgumentException("date of tasks to retrieve");
            }
            return new GetCommand(LocalDate.parse(commandArray[1].trim()));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException("date of tasks to retrieve");
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("yyyy-mm-dd");
        }
    }

    /**
     * Returns a FindCommand.
     * @param commandArray The array containing the input string.
     * @return A FindCommand.
     * @throws DukeException If the commandArray is invalid.
     */
    private static Command createFindCommand(String[] commandArray) throws DukeException {
        try {
            if (commandArray[1].trim().equals("")) {
                throw new MissingArgumentException("keyword to search");
            }
            return new FindCommand(commandArray[1].trim());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException("keyword to search");
        }
    }
}
