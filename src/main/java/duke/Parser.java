package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

/**
 * Represents a Parser in Duke application.
 * The Parser makes sense of user commands.
 */
public class Parser {

    /**
     * Parses the user command and returns the appropriate Command object.
     *
     * @param fullCommand The user command.
     * @return The appropriate Command object.
     * @throws DukeException If the command is invalid or in wrong format.
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else {
            //Splitting the full command into command and details.
            String[] cmdAndDetails = fullCommand.split(" ", 2);
            String cmd = cmdAndDetails[0];
            Command command = null;
            switch (cmd) {
            case "delete":
                command = createDeleteCommand(cmdAndDetails);
                break;
            case "done":
                command = createDoneCommand(cmdAndDetails);
                break;
            case "todo":
                command = createAddCommand(cmdAndDetails);
                break;
            case "deadline":
                command = createAddCommand(cmdAndDetails);
                break;
            case "event":
                command = createAddCommand(cmdAndDetails);
                break;
            case "find":
                command = createFindCommand(cmdAndDetails);
                break;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return command;
        }

    }

    /**
     * Returns the Command object of DeleteCommand type.
     *
     * @param cmdAndDetails The array containing command and description of the command.
     * @return The Command object of DeleteCommand type.
     * @throws DukeException If the command has missing information or in wrong format.
     */
    private static Command createDeleteCommand(String[] cmdAndDetails) throws DukeException {
        try {
            if (cmdAndDetails[1].trim().equals("")) {
                throw new DukeException("OOPS!!! The description of delete cannot be empty.");
            }
            String deleteTaskDescription = cmdAndDetails[1].trim();
            return new DeleteCommand(Integer.parseInt(deleteTaskDescription) - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of delete cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of delete have to be a number.");
        }
    }

    /**
     * Returns the Command object of DoneCommand type.
     *
     * @param cmdAndDetails The array containing command and description of the command.
     * @return The Command object of DoneCommand type.
     * @throws DukeException If the command has missing information or in wrong format.
     */
    private static Command createDoneCommand(String[] cmdAndDetails) throws DukeException {
        try {
            if (cmdAndDetails[1].trim().equals("")) {
                throw new DukeException("OOPS!!! The description of done cannot be empty.");
            }
            String doneTaskDescription = cmdAndDetails[1].trim();
            return new DoneCommand(Integer.parseInt(doneTaskDescription) - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of done cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of done have to be a number");
        }
    }

    /**
     * Returns the Command object of AddCommand type.
     *
     * @param cmdAndDetails The array containing command and description of the command.
     * @return The Command object of AddCommand type.
     * @throws DukeException If the command has missing information or in wrong format.
     */
    private static Command createAddCommand(String[] cmdAndDetails) throws DukeException {
        try {
            String taskDescription = cmdAndDetails[1];
            if (taskDescription.trim().equals("")) {
                throw new DukeException("OOPS!!! The description of a task cannot be empty.");
            }
            return new AddCommand(cmdAndDetails[0], cmdAndDetails[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of a task cannot be empty.");
        }
    }

    /**
     * Returns the Command object of FindCommand type.
     *
     * @param cmdAndDetails The array containing command and description of the command
     * @return The Command object of FindCommand type.
     * @throws DukeException If the command has missing information or in wrong format.
     */
    private static Command createFindCommand(String[] cmdAndDetails) throws DukeException {
        try {
            String findTaskDescription = cmdAndDetails[1];
            if (findTaskDescription.trim().equals("")) {
                throw new DukeException("OOPS!!! The description of find cannot be empty.");
            }
            return new FindCommand(findTaskDescription);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of find cannot be empty.");
        }
    }
}
