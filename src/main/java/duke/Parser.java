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
     * @throws DukeException If the command is not supported.
     */
    public static Command parse(String command) throws DukeException {
        Command cmd;
        String[] commandArray = command.split(" ", 2);
        switch (commandArray[0]) {
        case "todo":
            try {
                cmd = new AddCommand(commandArray[0], commandArray[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            try {
                cmd = new AddCommand(commandArray[0], commandArray[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The description and due date of a deadline cannot be empty.");
            }
            break;
        case "event":
            try {
                cmd = new AddCommand(commandArray[0], commandArray[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The description and date and time of an event cannot be empty.");
            }
            break;
        case "done":
            try {
                if (commandArray[1].trim().equals("")) {
                    throw new DukeException("The ID of the task done cannot be empty.");
                }
                cmd = new DoneCommand(Integer.parseInt(commandArray[1].trim()));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The ID of the task done cannot be empty.");
            } catch (NumberFormatException e) {
                throw new DukeException("The ID of the task done should be a number.");
            }
            break;
        case "update":
            try {
                if (commandArray[1].trim().equals("")) {
                    throw new DukeException("The ID and details of the task to update cannot be empty.");
                }
                String[] splitArray = commandArray[1].trim().split(" ", 2);
                cmd = new UpdateCommand(Integer.parseInt(splitArray[0].trim()), splitArray[1].trim());
            }  catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The ID and details of the task to update cannot be empty.");
            } catch (NumberFormatException e) {
                throw new DukeException("The ID of the task to update should be a number.");
            }
            break;
        case "delete":
            try {
                if (commandArray[1].trim().equals("")) {
                    throw new DukeException("The ID of the task to delete cannot be empty.");
                }
                cmd = new DeleteCommand(Integer.parseInt(commandArray[1].trim()));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The ID of the task to delete cannot be empty.");
            } catch (NumberFormatException e) {
                throw new DukeException("The ID of the task to delete should be a number.");
            }
            break;
        case "get":
            try {
                if (commandArray[1].trim().equals("")) {
                    throw new DukeException("The date of tasks to retrieve cannot be empty.");
                }
                cmd = new GetCommand(LocalDate.parse(commandArray[1].trim()));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The date of tasks to retrieve cannot be empty.");
            } catch (DateTimeParseException e) {
                throw new DukeException("Incorrect date format. Format required: yyyy-mm-dd");
            }
            break;
        case "find":
            try {
                if (commandArray[1].trim().equals("")) {
                    throw new DukeException("The keyword to search cannot be empty.");
                }
                cmd = new FindCommand(commandArray[1].trim());
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
