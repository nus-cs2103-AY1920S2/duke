package duke;

import duke.command.*;

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
    public static Command parse(String fullCommand) throws DukeException{
        if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else {
            String[] temp = fullCommand.split(" ", 2);
            String cmd = temp[0];
            Command command = null;
            switch (cmd) {
            case "delete":
                command = createDeleteCommand(temp);
                break;
            case "done":
                command = createDoneCommand(temp);
                break;
            case "todo":
                command = createAddCommand(temp);
                break;
            case "deadline":
                command = createAddCommand(temp);
                break;
            case "event":
                command = createAddCommand(temp);
                break;
            case "find":
                command = createFindCommand(temp);
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
     * @param temp The array containing command and description of the command.
     * @return The Command object of DeleteCommand type.
     * @throws DukeException If the command has missing information or in wrong format.
     */
    private static Command createDeleteCommand(String[] temp) throws DukeException {
        try {
            if (temp[1].trim().equals("")) {
                throw new DukeException("OOPS!!! The description of delete cannot be empty.");
            }
            String body = temp[1].trim();
            return new DeleteCommand(Integer.parseInt(body) - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of delete cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of delete have to be a number.");
        }
    }

    /**
     * Returns the Command object of DoneCommand type.
     *
     * @param temp The array containing command and description of the command.
     * @return The Command object of DoneCommand type.
     * @throws DukeException If the command has missing information or in wrong format.
     */
    private static Command createDoneCommand(String[] temp) throws DukeException {
        try {
            if (temp[1].trim().equals("")) {
                throw new DukeException("OOPS!!! The description of done cannot be empty.");
            }
            String body = temp[1].trim();
            return new DoneCommand(Integer.parseInt(body) - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of done cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of done have to be a number");
        }
    }

    /**
     * Returns the Command object of AddCommand type.
     *
     * @param temp The array containing command and description of the command.
     * @return The Command object of AddCommand type.
     * @throws DukeException If the command has missing information or in wrong format.
     */
    private static Command createAddCommand(String[] temp) throws DukeException {
        try {
            String body = temp[1];
            if (body.trim().equals("")) {
                throw new DukeException("OOPS!!! The description of a task cannot be empty.");
            }
            return new AddCommand(temp[0], temp[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of a task cannot be empty.");
        }
    }

    /**
     * Returns the Command object of FindCommand type.
     *
     * @param temp The array containing command and description of the command
     * @return The Command object of FindCommand type.
     * @throws DukeException If the command has missing information or in wrong format.
     */
    private static Command createFindCommand(String[] temp) throws DukeException {
        try {
            String body = temp[1];
            if (body.trim().equals("")) {
                throw new DukeException("OOPS!!! The description of find cannot be empty.");
            }
            return new FindCommand(body);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of find cannot be empty.");
        }
    }
}
