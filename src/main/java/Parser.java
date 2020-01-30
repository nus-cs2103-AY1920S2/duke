import java.util.Arrays;
import java.util.ArrayList;

public class Parser {

    private static ArrayList<String> validCommands = new ArrayList<>(
            Arrays.asList("list", "done", "delete", "todo", "event", "deadline", "bye"));

    public Parser() {
    }

    public static Command parse(String fullCommand) throws DukeException{
        String[] commands = fullCommand.split(" ", 2);
        String commandWord = commands[0];
        checkCommand(commandWord, commands);
        switch(commandWord) {
            case "bye":
                return new ExitCommand(commandWord);
            case "list":
                return new ListCommand(commandWord);
            case "done":
                int doneIndex = Integer.parseInt(commands[1]);
                return new DoneCommand(commandWord, doneIndex);
            case "delete":
                int deleteIndex = Integer.parseInt(commands[1]);
                return new DeleteCommand(commandWord, deleteIndex);
            default:
                return new AddCommand(commandWord, commands);
        }
    }

    public static void checkCommand(String commandWord, String[] commands) throws DukeException {
        checkCommandWord(commandWord);
        if (!commandWord.equals("list") && !commandWord.equals("bye")) {
            checkDetails(commands);
        }
    }

    public static void checkCommandWord(String commandWord) throws InvalidCommandException {
        if (!validCommands.contains(commandWord)) {
            throw new InvalidCommandException("Sorry dude but that won't command me!");
        }
    }

    public static void checkDetails(String[] commands) throws EmptyDescriptionException{
        if (commands.length < 2) {
            throw new EmptyDescriptionException("Sorry dude but where are the arguments???");
        }
    }
}
