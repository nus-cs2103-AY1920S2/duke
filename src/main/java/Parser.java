import java.time.LocalDate;
import java.util.ArrayList;

public class Parser {

    /**
     * Parse the command entered by user into a Command object if command exists
     * @param  command   the input by the user
     * @return the parsed command
     */
    public static Command parse(String command) throws DukeException {
        String[] commandTokens = command.split(" ");
        switch (commandTokens[0]) {
        case "bye":
            return new ExitCommand(command);
        case "list":
            return new ListCommand(command);
        case "done":
            return new DoneCommand(command);
        case "delete":
            return new DeleteCommand(command);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(command);
        default:
            throw new DukeException("☹ OOPS!!! Cannot parse command!");
        }
    }
}
