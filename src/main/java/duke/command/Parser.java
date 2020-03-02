package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

public class Parser {

    /**
     * Parses user input to determine what action to take.
     * @param fullCommand The command entered by the user
     * @return The Command corresponding to the command entered
     * @throws DukeException If an invalid command is provided
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] cmdArgs = fullCommand.split(" ", 2);
        Keyword keyword = Keyword.getKeyword(cmdArgs[0]);

        Command cmd;
        switch (keyword) {
        case BYE:
            cmd = new ByeCommand();
            break;
        case LIST:
            cmd = new ListCommand();
            break;
        case DONE:
            cmd = new DoneCommand(cmdArgs);
            break;
        case FIND:
            cmd = new FindCommand(cmdArgs);
            break;
        case DELETE:
            cmd = new DeleteCommand(cmdArgs);
            break;
        case DEADLINE:
            cmd = new DeadlineCommand(cmdArgs);
            break;
        case EVENT:
            cmd = new EventCommand(cmdArgs);
            break;
        case TODO:
            cmd = new TodoCommand(cmdArgs);
            break;
        default:
            throw new InvalidCommandException();
        }
        return cmd;
    }
}