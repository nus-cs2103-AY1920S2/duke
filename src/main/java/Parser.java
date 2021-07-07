import java.util.InvalidPropertiesFormatException;

/**
 * Represents a parser that takes in user-typed commands and
 * returns the equivalent Command object.
 */
public class Parser {

    /**
     * Returns a Command object based on the interpretation of the user-typed command.
     *
     * @param command the user-typed command
     * @return a Command corresponding to the command interpreted
     */
    public static Command parse(String command) {
        try {
            String firstWord = command.split(" ")[0];

            switch (firstWord) {
            case "bye":
                return new ExitCommand();
            case "deadline":
                return parseDeadline(getDetails(command, firstWord));
            case "delete":
                return parseDeleteTask(getDetails(command, firstWord));
            case "done":
                return parseCompleteTask(getDetails(command, firstWord));
            case "event":
                return parseEvent(getDetails(command, firstWord));
            case "list":
                return new ShowTasksCommand();
            case "todo":
                return parseTodo(getDetails(command, firstWord));
            case "find":
                return parseFind(getDetails(command, firstWord));
            case "snooze":
                return parseSnooze(getDetails(command, firstWord));
            default:
                return new InvalidCommand();
            }
        } catch (IndexOutOfBoundsException | InvalidPropertiesFormatException e) {
            return new InvalidFormatCommand();
        }
    }

    private static Command parseDeadline(String details) throws
            IndexOutOfBoundsException,
            InvalidPropertiesFormatException {
        String description = details.split(" /by ")[0];
        String deadline = details.split(" /by ")[1];

        return new AddDeadlineCommand(description, deadline);
    }

    private static Command parseEvent(String details) throws
            IndexOutOfBoundsException,
            InvalidPropertiesFormatException {
        String description = details.split(" /at ")[0];
        String time = details.split(" /at ")[1];

        return new AddEventCommand(description, time);
    }

    private static Command parseTodo(String details) {
        String description = details;
        if (!description.isEmpty()) {
            return new AddTodoCommand(description);
        } else {
            return new InvalidFormatCommand();
        }
    }

    private static Command parseDeleteTask(String details) {
        try {
            int index = Integer.parseInt(details) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            return new InvalidFormatCommand();
        }
    }

    private static Command parseCompleteTask(String details) {
        try {
            int index = Integer.parseInt(details) - 1;
            return new CompleteTaskCommand(index);
        } catch (NumberFormatException e) {
            return new InvalidFormatCommand();
        }
    }

    private static Command parseSnooze(String details) {
        try {
            String[] words = details.split(" ");
            int index = Integer.parseInt(words[0]) - 1;
            String newTime = getDetails(details, words[0]);

            return new SnoozeCommand(index, newTime);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return new InvalidFormatCommand();
        }
    }

    private static Command parseFind(String details) {
        return new FindCommand(details);
    }

    private static String getDetails(String command, String firstWord) throws IndexOutOfBoundsException {
        return command.split(firstWord)[1].trim();
    }
}
