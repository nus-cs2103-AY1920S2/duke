import java.util.HashMap;

/**
 * Deals with making sense of user commands.
 */
public class Parser {
    protected String command;
    protected String[] commandWords;
    protected int commandLength;

    protected HashMap<String, CommandType> validCommands;
    protected HashMap<String, String> commandDelimiter;
    protected HashMap<String, String> commandTypeFormatInfo;

    public Parser(String command) {
        // Remove leading and trailing whitespace
        this.command = command.trim();
        this.commandWords = this.command.split("\\s+");
        this.commandLength = this.command.length();
        setupValidCommands();
        setupCommandDelimiter();
        setupCommandTypeFormatInfo();
    }

    private void setupCommandTypeFormatInfo() {
        commandTypeFormatInfo = new HashMap<>();
        commandTypeFormatInfo.put("event", "Incorrect event format given... Correct format: event " +
                "[description] /at [event time in yyyy-mm-dd]");
        commandTypeFormatInfo.put("deadline", "Incorrect deadline task format given... Correct " +
                "format: Deadline task format: deadline [description] /by " +
                "[due date in yyyy-mm-dd]");
        commandTypeFormatInfo.put("todo", "Incorrect todo task format given... Correct format: todo " +
                "[description]");
    }

    private void setupCommandDelimiter() {
        commandDelimiter = new HashMap<>();
        commandDelimiter.put("deadline", "/by");
        commandDelimiter.put("event", "/at");
    }

    private void setupValidCommands() {
        this.validCommands = new HashMap<>();
        this.validCommands.put("deadline", CommandType.DEADLINE);
        this.validCommands.put("event", CommandType.EVENT);
        this.validCommands.put("todo", CommandType.TODO);
        this.validCommands.put("list", CommandType.LIST);
        this.validCommands.put("bye", CommandType.BYE);
        this.validCommands.put("done", CommandType.DONE);
        this.validCommands.put("delete", CommandType.DELETE);
    }

    /**
     * Get type of command stored in Parser instance.
     * @return first word of command, indicates command type
     * @throws DukeException for empty command
     */
    public String getCommandType() throws DukeException {
        if (commandLength == 0) {
            throw new DukeException("Empty command given...");
        }
        return commandWords[0];
    }

    /**
     * Obtain the task number for only done and delete commands.
     * @return the task number
     * @throws NumberFormatException when invalid task number is given for done command
     */
    public int getCommandTaskNumber() throws NumberFormatException {
        return Integer.parseInt(commandWords[1]);
    }

    public boolean isExitCommand() {
        return commandWords[0].equals("bye");
    }

    public boolean isEmptyCommand() {
        return commandLength == 0;
    }

    public boolean isValidCommand() {
        return validCommands.containsKey(commandWords[0]);
    }

    public int numberOfCommandArguments() {
        return commandWords.length - 1;
    }

    public String getDescription() throws DukeException {
        String commandType = commandWords[0];
        String description = null;
        if (commandWords.length == 1) {
            throw new DukeException(
                    String.format("The description of a %s cannot be empty!", commandType));
        } else if (commandType.equals("todo")) {
            description = command.substring(commandType.length() + 1);
        } else if (commandType.equals("deadline") || commandType.equals("event")) {
            // Check if required delimiter exists
            if (commandDelimiter.containsKey(commandType)) {
                String delimiter = commandDelimiter.get(commandType);
                int delimiterIndex = command.indexOf(delimiter);
                int delimiterLength = delimiter.length();
                // Account for space after command and before delimiter
                description = command.substring(commandType.length() + 1, delimiterIndex - 1);
            } else {
                // Invalid command format given
                throw new DukeException(commandTypeFormatInfo.get(commandType));
            }
        }
        return description;
    }

    public String getDueDate() {
        String commandType = commandWords[0];
        String delimiter = commandDelimiter.get(commandType);
        // Get first word's index for deadline
        // 1 additional character is considered for whitespace
        int delimiterLength = delimiter.length();
        int delimiterIndex = command.indexOf(delimiter);
        int delimiterStartIndex = delimiterIndex + delimiterLength + 1;
        return command.substring(delimiterStartIndex);
    }

    /**
     * Throws DukeException if event command has invalid parameters.
     * @throws DukeException for invalid event command
     */
    public void verifyEventInput() throws DukeException {
        String eventDelimiter = commandDelimiter.get(commandWords[0]);
        int eventDelimiterIndex = command.indexOf(eventDelimiter);
        int eventDelimiterLength = eventDelimiter.length();

        if (commandLength == "event".length()) {
            // Empty event command given (e.g. "event")
            throw new DukeException(DukeException.exceptionIcon +
                    " Wrong input format for adding an event... " +
                    "Format: event [description] /at [event time]");
        }
        if (!command.contains(eventDelimiter)) {
            // No delimiter present (e.g. "event project meeting Mon 2-4pm")
            throw new DukeException(DukeException.exceptionIcon +
                    " Wrong input format for adding an event... " +
                    "Format: event [description] /at [event time]");
        }
        if (eventDelimiterIndex + eventDelimiterLength == commandLength) {
            // Delimiter is at the end of command (e.g. "event /at")
            throw new DukeException(DukeException.exceptionIcon +
                    " Wrong input format for adding an event... " +
                    "Format: event [description] /at [event time]");
        }
    }

    /**
     * Throws DukeException if deadline command has any invalid parameters.
     * @throws DukeException for invalid deadline command
     */
    public void verifyDeadlineInput() throws DukeException {
        String deadlineDelimiter = commandDelimiter.get(commandWords[0]);
        int deadlineDelimiterIndex = command.indexOf(deadlineDelimiter);
        int deadlineDelimiterLength = deadlineDelimiter.length();

        if (commandLength == "deadline".length()) {
            // Empty deadline command given (e.g. "deadline")
            throw new DukeException(DukeException.exceptionIcon +
                    " The description of a deadline cannot be empty...");
        }
        if (!command.contains(deadlineDelimiter)) {
            // No due date given (e.g. "deadline read book")
            throw new DukeException(DukeException.exceptionIcon +
                    " No deadline given... Format: deadline [description] /by [due by]");
        }
        if (deadlineDelimiterIndex + deadlineDelimiterLength == commandLength) {
            // Delimiter is at the end of command (e.g. "deadline /by")
            throw new DukeException(DukeException.exceptionIcon +
                    " No deadline given... Format: deadline [description] /by [due by]");
        }
    }
}
