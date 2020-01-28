package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.GetCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Makes sense of the user command.
 */
public class Parser {
    /**
     * Parses the specified command and returns the corresponding Command object.
     * @param command The command to be parsed.
     * @return The corresponding Command object.
     * @throws DukeException If the command is not supported.
     */
    public static Command parse(String command) throws DukeException {
        Command cmd;
        String[] commandArr = command.split(" ", 2);
        switch(commandArr[0]) {
        case "todo":
            try {
                cmd = new AddCommand(commandArr[0], commandArr[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            try {
                cmd = new AddCommand(commandArr[0], commandArr[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The description and due date of a deadline cannot be empty.");
            }
            break;
        case "event":
            try {
                cmd = new AddCommand(commandArr[0], commandArr[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The description and date and time of an event cannot be empty.");
            }
            break;
        case "done":
            try {
                if (commandArr[1].trim().equals("")) {
                    throw new DukeException("The ID of the task done cannot be empty.");
                }
                cmd = new DoneCommand(Integer.parseInt(commandArr[1].trim()));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The ID of the task done cannot be empty.");
            } catch (NumberFormatException e) {
                throw new DukeException("The ID of the task done should be a number.");
            }
            break;
        case "delete":
            try {
                if (commandArr[1].trim().equals("")) {
                    throw new DukeException("The ID of the task to delete cannot be empty.");
                }
                cmd = new DeleteCommand(Integer.parseInt(commandArr[1].trim()));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The ID of the task to delete cannot be empty.");
            } catch (NumberFormatException e) {
                throw new DukeException("The ID of the task to delete should be a number.");
            }
            break;
        case "get":
            try {
                if (commandArr[1].trim().equals("")) {
                    throw new DukeException("The date of tasks to retrieve cannot be empty.");
                }
                cmd = new GetCommand(LocalDate.parse(commandArr[1].trim()));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The date of tasks to retrieve cannot be empty.");
            } catch (DateTimeParseException e) {
                throw new DukeException("Incorrect date format. Format required: yyyy-mm-dd");
            }
            break;
        case "find":
            try {
                if (commandArr[1].trim().equals("")) {
                    throw new DukeException("The keyword to search cannot be empty.");
                }
                cmd = new FindCommand(commandArr[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The keyword to search cannot be empty.");
            }
            break;
        case "list":
            cmd = new ListCommand();
            break;
        case "bye":
            cmd = new ExitCommand();
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return cmd;
    }
}
