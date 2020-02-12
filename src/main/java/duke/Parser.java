package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UndoCommand;
import duke.exception.DukeException;
import duke.exception.DukeMissingArgumentException;
import duke.exception.DukeNumberFormatException;
import duke.exception.Messages;

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
        if (fullCommand.trim().equals("list")) {
            return new ListCommand();
        } else if (fullCommand.trim().equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.trim().equals("undo")) {
            return new UndoCommand();
        } else {
            //Splitting the full command into command and details.
            String[] cmdAndDetails = fullCommand.split(" ", 2);
            String cmd = cmdAndDetails[0];
            Command command;
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
                throw new DukeException(Messages.MESSAGE_INVALID_COMMAND);
            }
            return command;
        }

    }

    private static boolean isEmptyDescription(String description) {
        return description.equals("");
    }

    /**
     * Tests the validity of the details of command and throws relevant exceptions if invalid.
     *
     * @param command The specified command.
     * @param cmdAndDetails The array containing command and description of the command.
     * @throws DukeException If the command has missing information or in wrong format.
     */
    private static void testValidityOfInput(String command, String[] cmdAndDetails) throws DukeException {
        try {
            String taskDescription = cmdAndDetails[1].trim();
            if (isEmptyDescription(taskDescription)) {
                throw new DukeMissingArgumentException(String.format(
                        Messages.MESSAGE_MISSING_COMMAND_DESCRIPTION, command));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException(String.format(
                    Messages.MESSAGE_MISSING_COMMAND_DESCRIPTION, command));
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
        String command = cmdAndDetails[0];
        testValidityOfInput(command, cmdAndDetails);
        try {
            String deleteTaskDescription = cmdAndDetails[1].trim();
            return new DeleteCommand(Integer.parseInt(deleteTaskDescription) - 1);
        } catch (NumberFormatException e) {
            throw new DukeNumberFormatException(command);
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
        String command = cmdAndDetails[0];
        testValidityOfInput(command, cmdAndDetails);
        try {
            String doneTaskDescription = cmdAndDetails[1].trim();
            return new DoneCommand(Integer.parseInt(doneTaskDescription) - 1);
        } catch (NumberFormatException e) {
            throw new DukeNumberFormatException(command);
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
        testValidityOfInput("task", cmdAndDetails);
        String taskDescription = cmdAndDetails[1].trim();
        return new AddCommand(cmdAndDetails[0], taskDescription);
    }

    /**
     * Returns the Command object of FindCommand type.
     *
     * @param cmdAndDetails The array containing command and description of the command
     * @return The Command object of FindCommand type.
     * @throws DukeException If the command has missing information or in wrong format.
     */
    private static Command createFindCommand(String[] cmdAndDetails) throws DukeException {
        testValidityOfInput("find", cmdAndDetails);
        String findTaskDescription = cmdAndDetails[1].trim();
        return new FindCommand(findTaskDescription);
    }
}
