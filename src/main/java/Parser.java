import java.util.Arrays;

public class Parser {
    public static String[] tasksCommandList = {"todo", "deadline", "event"};

    public Parser() { }

    public static Command parse(String command) throws DukeException {
        String[] split = command.split(" ", 2);
        String commandType = split[0];
        if (split.length < 1) {
            throw new DukeException("The description cannot be empty.");
        }

        if (commandType.equals("bye")) {
            return new ByeCommand();
        } else if (commandType.equals("list")) {
            return new ListCommand();
        } else if (commandType.equals("done")) {
            return new DoneCommand(Integer.parseInt(split[1]) - 1);
        } else if (commandType.equals("delete")) {
            return new DeleteCommand(Integer.parseInt(split[1]) - 1);
        } else if (Arrays.asList(tasksCommandList).contains(commandType)) {
            return new AddCommand(commandType, split[1]);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}