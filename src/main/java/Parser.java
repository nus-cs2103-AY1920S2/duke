import java.time.format.DateTimeParseException;

/**
 * Parses user input.
 */
public class Parser {

    public Command parseCommand(String userCommandText) {
        String[] words = userCommandText.split(" ");
        String commandWord = words[0];
        String argumentText = words.length == 1 ? "" : Parser.getArguments(words);

        try {
            switch (commandWord) {
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case DoneCommand.COMMAND_WORD:
                return new DoneCommand(words);
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(words);
            case TodoCommand.COMMAND_WORD:
                return new TodoCommand(argumentText);
            case DeadlineCommand.COMMAND_WORD:
                return new DeadlineCommand(argumentText);
            case EventCommand.COMMAND_WORD:
                return new EventCommand(argumentText);
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case EditCommand.COMMAND_WORD:
                return new EditCommand(argumentText);
            case FindCommand.COMMAND_WORD:
                return new FindCommand(argumentText);
            default:
                throw new DukeException("I beg your pardon, I do not know what that means.");
            }
        } catch (DukeException | DateTimeParseException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    /**
     * Combines a String array from index 1 to (length - 1)
     */
    public static String getArguments(String[] parts) {
        String result = parts[1];
        for (int i = 2; i < parts.length; i++) {
            result += " " + parts[i];
        }
        return result;
    }
}
