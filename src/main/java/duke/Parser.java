package duke;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EmptyInputCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.DateTimeException;
import java.util.HashMap;

/**
 * Deals with making sense of user commands.
 */
public class Parser {
    protected static HashMap<String, String> commandDelimiter;
    protected static HashMap<String, String> commandTypeFormatInfo;

    /**
     * Creates a new command based on given input string.
     *
     * @param fullCommand represents a user input command
     * @return Command based on action to be performed
     * @throws DukeException when given input is not a valid command
     */
    public static Command parse(String fullCommand) throws DukeException {
        setupCommandDelimiter();
        setupCommandTypeFormatInfo();
        // Remove leading and trailing whitespace
        fullCommand = fullCommand.trim();
        String[] commandWords = fullCommand.split("\\s+");
        int numberOfCommandArguments = commandWords.length - 1;
        Command outputCommand;
        // Check for empty command
        if (fullCommand.length() == 0) {
            outputCommand = new EmptyInputCommand();
            return outputCommand;
        }

        switch (commandWords[0]) {
        case "bye":
            outputCommand = new ExitCommand();
            break;
        case "list":
            outputCommand = new ListCommand();
            break;
        case "find":
            if (numberOfCommandArguments == 0) {
                throw new DukeException(DukeException.exceptionIcon
                        + " The description of a find cannot be empty...");
            }
            outputCommand = new FindCommand(Parser.getDescription(fullCommand, commandWords));
            break;
        case "done":
            try {
                int taskNumber = Integer.parseInt(commandWords[1]);
                outputCommand = new DoneCommand(taskNumber);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("Invalid Task Number given!");
            }
            break;
        case "todo":
            // Check if valid command is given
            if (numberOfCommandArguments == 0) {
                throw new DukeException(DukeException.exceptionIcon
                        + " The description of a todo cannot be empty...");
            }
            String todoDescription = Parser.getDescription(fullCommand, commandWords);
            outputCommand = new AddTaskCommand(new Todo(todoDescription));
            break;
        case "deadline":
            // Verify user input
            Parser.verifyDeadlineInput(fullCommand, commandWords);
            String deadlineDescription = Parser.getDescription(fullCommand, commandWords);
            String deadline = Parser.getDueDate(fullCommand, commandWords);
            try {
                outputCommand = new AddTaskCommand(new Deadline(deadlineDescription, deadline));
            } catch (DateTimeException e) {
                // Given deadline string was not in correct format
                throw new DukeException("Given deadline task due date was not in correct format"
                        + ": [yyyy-mm-dd]");
            }
            break;
        case "event":
            Parser.verifyEventInput(fullCommand, commandWords);
            String eventDescription = Parser.getDescription(fullCommand, commandWords);
            String eventTime = Parser.getDueDate(fullCommand, commandWords);
            // Add new event to task list
            try {
                outputCommand = new AddTaskCommand(new Event(eventDescription, eventTime));
            } catch (DateTimeException e) {
                // Given event time could not be converted a valid date
                throw new DukeException("Given event task due date was not in correct format"
                        + ": [yyyy-mm-dd]");
            }
            break;
        case "delete":
            try {
                int taskNumberToDelete = Integer.parseInt(commandWords[1]);
                outputCommand = new DeleteCommand(taskNumberToDelete);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("Invalid task number given for deletion...");
            }
            break;
        default:
            // Invalid command type given
            // First word of command does not match list of valid commands
            throw new DukeException(DukeException.exceptionIcon
                    + " OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return outputCommand;
    }

    /**
     * Throws duke.DukeException if event command has invalid parameters.
     *
     * @throws DukeException for invalid event command
     */
    public static void verifyEventInput(String command, String[] commandWords) throws DukeException {
        String eventDelimiter = commandDelimiter.get(commandWords[0]);
        int eventDelimiterIndex = command.indexOf(eventDelimiter);
        int eventDelimiterLength = eventDelimiter.length();
        int commandLength = command.length();

        if (commandLength == "event".length()) {
            // Empty event command given (e.g. "event")
            throw new DukeException(DukeException.exceptionIcon
                    + " Wrong input format for adding an event... "
                    + "Format: event [description] /at [event time]");
        }
        if (!command.contains(eventDelimiter)) {
            // No delimiter present (e.g. "event project meeting Mon 2-4pm")
            throw new DukeException(DukeException.exceptionIcon
                    + " Wrong input format for adding an event... "
                    + "Format: event [description] /at [event time]");
        }
        if (eventDelimiterIndex + eventDelimiterLength == commandLength) {
            // Delimiter is at the end of command (e.g. "event /at")
            throw new DukeException(DukeException.exceptionIcon
                    + " Wrong input format for adding an event... "
                    + "Format: event [description] /at [event time]");
        }
    }

    /**
     * Throws duke.DukeException if deadline command has any invalid parameters.
     *
     * @throws DukeException for invalid deadline command
     */
    public static void verifyDeadlineInput(String command, String[] commandWords) throws DukeException {
        String deadlineDelimiter = commandDelimiter.get(commandWords[0]);
        int deadlineDelimiterIndex = command.indexOf(deadlineDelimiter);
        int deadlineDelimiterLength = deadlineDelimiter.length();
        int commandLength = command.length();

        if (commandLength == "deadline".length()) {
            // Empty deadline command given (e.g. "deadline")
            throw new DukeException(DukeException.exceptionIcon
                    + " The description of a deadline cannot be empty...");
        }
        if (!command.contains(deadlineDelimiter)) {
            // No due date given (e.g. "deadline read book")
            throw new DukeException(DukeException.exceptionIcon
                    + " No deadline given... Format: deadline [description] /by [due by]");
        }
        if (deadlineDelimiterIndex + deadlineDelimiterLength == commandLength) {
            // Delimiter is at the end of command (e.g. "deadline /by")
            throw new DukeException(DukeException.exceptionIcon
                    + " No deadline given... Format: deadline [description] /by [due by]");
        }
    }

    /**
     * Returns the due date information associated with the given command parameter.
     *
     * @param command      string representing user input
     * @param commandWords list of words that make up command
     * @return String representing the due date component of command
     */
    public static String getDueDate(String command, String[] commandWords) {
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
     * Returns a String representing the description component of a given command parameter.
     *
     * @param command      string representing user input
     * @param commandWords list of words that make up command
     * @return String representing the description component of command
     * @throws DukeException command is not in a valid format
     */
    public static String getDescription(String command, String[] commandWords) throws DukeException {
        String commandType = commandWords[0];
        String description = null;
        if (commandWords.length == 1) {
            throw new DukeException(
                    String.format("The description of a %s cannot be empty!", commandType));
        } else if (commandType.equals("todo") || commandType.equals("find")) {
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

    private static void setupCommandTypeFormatInfo() {
        commandTypeFormatInfo = new HashMap<>();
        commandTypeFormatInfo.put("event", "Incorrect event format given... Correct format: event "
                + "[description] /at [event time in yyyy-mm-dd]");
        commandTypeFormatInfo.put("deadline", "Incorrect deadline task format given... Correct "
                + "format: duke.task.Deadline task format: deadline [description] /by "
                + "[due date in yyyy-mm-dd]");
        commandTypeFormatInfo.put("todo", "Incorrect todo task format given... Correct format: todo "
                + "[description]");
    }

    private static void setupCommandDelimiter() {
        commandDelimiter = new HashMap<>();
        commandDelimiter.put("deadline", "/by");
        commandDelimiter.put("event", "/at");
    }
}
