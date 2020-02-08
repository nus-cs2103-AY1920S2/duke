package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.DukeInvalidCommandException;
import duke.exception.DukeInvalidDateException;
import duke.exception.DukeMissingInfoException;
import duke.exception.DukeNumberFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param fullCommand full user input string.
     * @return the command based on user input.
     * @throws DukeException if the user input is incomplete or in the wrong format.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] partialCommands = splitCommand(fullCommand);
        switch (partialCommands[0]) {
        case "bye":
            return getByeCommand();
        case "list":
            return getListCommand();
        case "delete":
            return getDeleteCommand(partialCommands);
        case "done":
            return getDoneCommand(partialCommands);
        case "todo":
            return getTodoCommand(partialCommands);
        case "event":
            return getEventCommand(partialCommands);
        case "deadline":
            return getDeadlineCommand(partialCommands);
        case "find":
            return getFindCommand(partialCommands);
        default:
            throw new DukeInvalidCommandException();
        }
    }

    /**
     * Splits the full command into 2 parts, the task and the rest of the information.
     *
     * @param fullCommand the input from the user.
     * @return the array of the 2 parts of the command.
     */
    private static String[] splitCommand(String fullCommand) {
        return fullCommand.split(" ", 2);
    }

    /**
     * Handles "find" input.
     *
     * @param partialCommands the array of the command that has been split.
     * @return FindCommand object.
     * @throws DukeMissingInfoException when there is no keyword provided.
     */
    private static Command getFindCommand(String[] partialCommands) throws DukeMissingInfoException {
        try {
            if (partialCommands[1].equals("")) {
                throw new DukeMissingInfoException(partialCommands[0]);
            }
            return new FindCommand(partialCommands[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingInfoException(partialCommands[0]);
        }
    }

    /**
     * Handles "deadline" input.
     *
     * @param partialCommands the array of the command that has been split.
     * @return AddCommand object for Deadline.
     * @throws DukeInvalidDateException when the format of the date is wrong.
     * @throws DukeMissingInfoException when the description or date is not given.
     */
    private static Command getDeadlineCommand(String[] partialCommands)
            throws DukeInvalidDateException, DukeMissingInfoException {
        try {
            String[] details = partialCommands[1].split(" /by ");
            return new AddCommand(new Deadline(details[0], LocalDate.parse(details[1])));
        } catch (DateTimeException e) {
            throw new DukeInvalidDateException();
        } catch (Exception e) {
            throw new DukeMissingInfoException(partialCommands[0]);
        }
    }

    /**
     * Handles "event" input.
     *
     * @param partialCommands the array of the command that has been split.
     * @return AddCommand object for Event.
     * @throws DukeInvalidDateException when the format of the date is wrong.
     * @throws DukeMissingInfoException when the description or date is not given.
     */
    private static Command getEventCommand(String[] partialCommands)
            throws DukeInvalidDateException, DukeMissingInfoException {
        try {
            String[] details = partialCommands[1].split(" /at ");
            return new AddCommand(new Event(details[0], LocalDate.parse(details[1])));
        } catch (DateTimeException e) {
            throw new DukeInvalidDateException();
        } catch (Exception e) {
            throw new DukeMissingInfoException(partialCommands[0]);
        }
    }

    /**
     * Handles "todo" input.
     *
     * @param partialCommands the array of the command that has been split.
     * @return AddCommand object for Todo.
     * @throws DukeMissingInfoException when the description is not given.
     */
    private static Command getTodoCommand(String[] partialCommands) throws DukeMissingInfoException {
        try {
            if (partialCommands[1].equals("")) {
                throw new DukeMissingInfoException(partialCommands[0]);
            }
            return new AddCommand(new Todo(partialCommands[1]));
        } catch (Exception e) {
            throw new DukeMissingInfoException(partialCommands[0]);
        }
    }

    /**
     * Handles "done" input.
     *
     * @param partialCommands the array of the command that has been split.
     * @return DoneCommand object.
     * @throws DukeNumberFormatException when the task is not given in terms of number.
     * @throws DukeMissingInfoException  when the task number is not given.
     */
    private static Command getDoneCommand(String[] partialCommands)
            throws DukeNumberFormatException, DukeMissingInfoException {
        try {
            return new DoneCommand(Integer.parseInt(partialCommands[1]) - 1);
        } catch (NumberFormatException e) {
            throw new DukeNumberFormatException();
        } catch (Exception e) {
            throw new DukeMissingInfoException(partialCommands[0]);
        }
    }

    /**
     * Handles "delete" input.
     *
     * @param partialCommands the array of the command that has been split.
     * @return DeleteCommand object.
     * @throws DukeMissingInfoException  when the task number is not given.
     * @throws DukeNumberFormatException when the task is not given in terms of number.
     */
    private static Command getDeleteCommand(String[] partialCommands)
            throws DukeMissingInfoException, DukeNumberFormatException {
        try {
            return new DeleteCommand(Integer.parseInt(partialCommands[1]) - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingInfoException(partialCommands[0]);
        } catch (NumberFormatException e) {
            throw new DukeNumberFormatException();
        }
    }

    private static Command getListCommand() {
        return new ListCommand();
    }

    private static Command getByeCommand() {
        return new ExitCommand();
    }
}
