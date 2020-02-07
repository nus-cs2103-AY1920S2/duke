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
        String[] partialCommands = fullCommand.split(" ", 2);
        Command toReturn;
        switch (partialCommands[0]) {
        case "bye":
            toReturn = new ExitCommand();
            break;
        case "list":
            toReturn = new ListCommand();
            break;
        case "delete":
            try {
                toReturn = new DeleteCommand(Integer.parseInt(partialCommands[1]) - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInfoException(partialCommands[0]);
            } catch (NumberFormatException e) {
                throw new DukeNumberFormatException();
            }
            break;
        case "done":
            try {
                toReturn = new DoneCommand(Integer.parseInt(partialCommands[1]) - 1);
            } catch (NumberFormatException e) {
                throw new DukeNumberFormatException();
            } catch (Exception e) {
                throw new DukeMissingInfoException(partialCommands[0]);
            }
            break;
        case "todo":
            try {
                if (partialCommands[1].equals("")) {
                    throw new DukeMissingInfoException(partialCommands[0]);
                }
                toReturn = new AddCommand(new Todo(partialCommands[1]));
            } catch (Exception e) {
                throw new DukeMissingInfoException(partialCommands[0]);
            }
            break;
        case "event":
            try {
                String[] details = partialCommands[1].split(" /at ");
                toReturn = new AddCommand(new Event(details[0], LocalDate.parse(details[1])));
            } catch (DateTimeException e) {
                throw new DukeInvalidDateException();
            } catch (Exception e) {
                throw new DukeMissingInfoException(partialCommands[0]);
            }
            break;
        case "deadline":
            try {
                String[] details = partialCommands[1].split(" /by ");
                toReturn = new AddCommand(new Deadline(details[0], LocalDate.parse(details[1])));
            } catch (DateTimeException e) {
                throw new DukeInvalidDateException();
            } catch (Exception e) {
                throw new DukeMissingInfoException(partialCommands[0]);
            }
            break;
        case "find":
            try {
                if (partialCommands[1].equals("")) {
                    throw new DukeMissingInfoException(partialCommands[0]);
                }
                toReturn = new FindCommand(partialCommands[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInfoException(partialCommands[0]);
            }
            break;
        default:
            throw new DukeInvalidCommandException();
        }
        return toReturn;
    }
}
