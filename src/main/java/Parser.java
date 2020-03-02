import java.util.Arrays;

/**
 * Parser class takes in and parses user input to return commands.
 */
public class Parser {
    public static String[] tasksCommandList = {"todo", "deadline", "event"};

    /**
     * Parses user input to return a command.
     * @param command user's input to specify command.
     * @return Command command specified by user.
     * @throws DukeException if user input does not follow input format.
     */
    public static Command parse(String command) throws DukeException {
        String[] split = command.split(" ", 2);
        String commandType = split[0];

        switch (commandType) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            assert split[1] != null : "The index of task should be entered to mark task as done.";
            checkInputException(split, commandType);
            return new DoneCommand(Integer.parseInt(split[1]) - 1);
        case "delete":
            assert split[1] != null : "The index of task should be entered to delete task.";
            checkInputException(split, commandType);
            return new DeleteCommand(Integer.parseInt(split[1]) - 1);
        case "find":
            assert split[1] != null : "The keyword should be entered search task list.";
            checkInputException(split, commandType);
            return new FindCommand(split[1]);
        case "update":
            assert split[1] != null : "The index of task should be entered to update tasks.";
            checkInputException(split, commandType);
            return new UpdateCommand(split[1]);
        case "todo":
        case "deadline":
        case "event":
            checkInputException(split, commandType);
            return new AddCommand(commandType, split[1]);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Checks correctness of user input.
     * Done and delete command must have index number input.
     * Add task command must have task description.
     * @param split command split into respective keywords.
     * @param commandType type of command.
     * @throws DukeException if user input does not follow input format.
     */
    public static void checkInputException(String[] split, String commandType) throws DukeException {
        if (split.length <= 1) {
            switch (commandType) {
            case "done":
            case "delete":
            case "update":
                throw new DukeException("☹ OOPS!!! Task number cannot be empty.");
            case "todo":
            case "deadline":
            case "event":
                throw new DukeException("☹ OOPS!!! Task description cannot be empty.");
            case "find":
                throw new DukeException("☹ OOPS!!! Search keyword cannot be empty.");
            default:
            }
        }
    }
}