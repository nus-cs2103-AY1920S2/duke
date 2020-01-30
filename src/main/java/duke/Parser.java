package duke;

/**
 * Parser class has parses the input and returns the appropiate command.
 */
public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        String line = "-----------------------------------";
        String[] strArr = fullCommand.split(" ");
        String command = strArr[0];
        int length = strArr.length;
        switch (command) {
            case "bye":
                return new ExitCommand(fullCommand);
            case "list":
                return new ListCommand(fullCommand);
            case "done":
                if (length > 1) {
                    return new DoneCommand(fullCommand);
                } else {
                    throw new DukeException("empty", command);
                }
            case "deadline":
            case "todo":
            case "event":
                if (length > 1) {
                    return new AddCommand(fullCommand);
                } else {
                    throw new DukeException("empty", command);
                }
            case "delete":
                if (length > 1) {
                    return new DeleteCommand(fullCommand);
                } else {
                    throw new DukeException("empty", command);
                }
            case "find":
                if (length > 1) {
                    return new FindCommand(fullCommand);
                } else {
                    throw new DukeException("empty", command);
                }
            default:
                throw new DukeException("invalid", command);
        }
    }
}
