package duke;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EmptyInputCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UndoCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.DateTimeException;
import java.util.HashMap;
import java.util.Optional;

/**
 * Deals with making sense of user commands.
 */
public class Parser {
    protected static HashMap<String, String> commandDelimiter = setupCommandDelimiter();
    protected static HashMap<String, String> commandTypeFormatInfo = setupCommandTypeFormatInfo();

    /**
     * Creates a new command based on given input string.
     *
     * @param command represents a user input command
     * @return Optional instance of Command based on action to be performed
     * @throws DukeException when given input is not a valid command
     */
    public static Optional<Command> parse(String command) throws DukeException {
        Optional<Command> outputCommand;
        String fullCommand = command.trim();
        String[] commandWords = fullCommand.split("\\s+");
        String firstCommandWord = commandWords[0];
        int numberOfCommandArguments = commandWords.length - 1;
        // Check for empty command
        if (fullCommand.length() == 0) {
            return Optional.of(new EmptyInputCommand());
        }

        switch (firstCommandWord) {
        case "bye":
            outputCommand = Optional.of(new ExitCommand());
            break;
        case "undo":
            outputCommand = Optional.of(new UndoCommand());
            break;
        case "ls":
            // Fallthrough, shortcut command for "list"
        case "list":
            outputCommand = Optional.of(new ListCommand());
            break;
        case "find":
            outputCommand = getFindCommand(fullCommand, commandWords, numberOfCommandArguments);
            break;
        case "done":
            outputCommand = getDoneCommand(commandWords);
            break;
        case "todo":
            outputCommand = getTodoCommand(fullCommand, commandWords, numberOfCommandArguments);
            break;
        case "deadline":
            outputCommand = getDeadlineCommand(fullCommand, commandWords);
            break;
        case "event":
            outputCommand = getEventCommand(fullCommand, commandWords);
            break;
        case "rm":
            // Fallthrough, shortcut command for "delete"
        case "delete":
            outputCommand = getDeleteCommand(commandWords);
            break;
        default:
            // First word of command does not match list of valid commands
            throw new DukeException(commandTypeFormatInfo.get("unknown"));
        }
        return outputCommand;
    }

    /**
     * Returns an Optional Command representing a Todo Command.
     *
     * @param fullCommand              Entire Command
     * @param commandWords             List of words present in Command
     * @param numberOfCommandArguments Argument count for Command
     * @return Optional Command representing Todo
     * @throws DukeException Given command does not have a description
     */
    private static Optional<Command> getTodoCommand(String fullCommand, String[] commandWords,
                                                    int numberOfCommandArguments) throws DukeException {
        Optional<Command> outputCommand;
        if (numberOfCommandArguments == 0) {
            throw new DukeException(commandTypeFormatInfo.get("todo"));
        }
        String todoDescription = Parser.getDescription(fullCommand, commandWords);
        outputCommand = Optional.of(new AddTaskCommand(new Todo(todoDescription)));
        return outputCommand;
    }

    /**
     * Returns an Optional Command representing a Find Command.
     *
     * @param fullCommand              Entire Command
     * @param commandWords             List of words present in Command
     * @param numberOfCommandArguments Argument count for Command
     * @return Optional Command representing Find Command
     * @throws DukeException Given command does not have a search term
     */
    private static Optional<Command> getFindCommand(String fullCommand, String[] commandWords,
                                                    int numberOfCommandArguments) throws DukeException {
        Optional<Command> outputCommand;
        if (numberOfCommandArguments == 0) {
            throw new DukeException(commandTypeFormatInfo.get("find"));
        }
        outputCommand = Optional.of(new FindCommand(
                Parser.getDescription(fullCommand, commandWords)));
        return outputCommand;
    }

    /**
     * Returns an Optional Command representing a Done Command.
     *
     * @param commandWords Words present in Command
     * @return Optional Command representing a Done Command
     * @throws DukeException Given command does not have a valid task number
     */
    private static Optional<Command> getDoneCommand(String[] commandWords) throws DukeException {
        Optional<Command> outputCommand;
        if (commandWords.length == 1) {
            throw new DukeException(commandTypeFormatInfo.get("missingTaskNumber"));
        }
        try {
            int taskNumber = Integer.parseInt(commandWords[1]);
            outputCommand = Optional.of(new DoneCommand(taskNumber));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(commandTypeFormatInfo.get("missingTaskNumber"));
        }
        return outputCommand;
    }

    /**
     * Returns an Optional Command representing a Delete Command.
     *
     * @param commandWords Words present in Command
     * @return Optional Command representing a Delete Command
     * @throws DukeException Given command does not have a valid task number
     */
    private static Optional<Command> getDeleteCommand(String[] commandWords) throws DukeException {
        Optional<Command> outputCommand;
        if (commandWords.length == 1) {
            throw new DukeException(commandTypeFormatInfo.get("missingTaskNumber"));
        }
        try {
            int taskNumberToDelete = Integer.parseInt(commandWords[1]);
            outputCommand = Optional.of(new DeleteCommand(taskNumberToDelete));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(commandTypeFormatInfo.get("missingTaskNumber"));
        }
        return outputCommand;
    }

    /**
     * Returns an Optional Command representing an Event Command.
     *
     * @param fullCommand  Command given for parsing
     * @param commandWords List of words present in Command
     * @return Optional of Command representing Event Command
     * @throws DukeException Given command does not have a valid date format
     */
    private static Optional<Command> getEventCommand(String fullCommand,
                                                     String[] commandWords) throws DukeException {
        Optional<Command> outputCommand;
        Parser.verifyEventInput(fullCommand, commandWords);
        String eventDescription = Parser.getDescription(fullCommand, commandWords);
        String eventTime = Parser.getDueDate(fullCommand, commandWords);
        try {
            outputCommand = Optional.of(new AddTaskCommand(
                    new Event(eventDescription, eventTime)));
        } catch (DateTimeException e) {
            throw new DukeException(commandTypeFormatInfo.get("event"));
        }
        return outputCommand;
    }

    /**
     * Returns an Optional Command representing a Deadline.
     *
     * @param fullCommand  Command given for parsing
     * @param commandWords List of words present in Command
     * @return Optional of Command representing Deadline Command
     * @throws DukeException Given command does not have a valid date format
     */
    private static Optional<Command> getDeadlineCommand(String fullCommand,
                                                        String[] commandWords) throws DukeException {
        Optional<Command> outputCommand;
        Parser.verifyDeadlineInput(fullCommand, commandWords);
        String deadlineDescription = Parser.getDescription(fullCommand, commandWords);
        String deadline = Parser.getDueDate(fullCommand, commandWords);
        try {
            outputCommand = Optional.of(new AddTaskCommand(
                    new Deadline(deadlineDescription, deadline)));
        } catch (DateTimeException e) {
            throw new DukeException(commandTypeFormatInfo.get("deadline"));
        }
        return outputCommand;
    }

    /**
     * Throws duke.exception.DukeException if event command has invalid parameters.
     *
     * @throws DukeException for invalid event command
     */
    protected static void verifyEventInput(String command, String[] commandWords) throws DukeException {
        String eventDelimiter = commandDelimiter.get(commandWords[0]);
        int eventDelimiterIndex = command.indexOf(eventDelimiter);
        int eventDelimiterLength = eventDelimiter.length();
        int commandLength = command.length();
        String errorMessage = commandTypeFormatInfo.get("event");
        int commandWordCount = commandWords.length;

        if (eventDelimiterIndex + eventDelimiterLength == commandLength) {
            // Delimiter is at the end of command (e.g. "event /at")
            throw new DukeException(errorMessage);
        }
        if (commandWordCount < 4) {
            throw new DukeException(errorMessage);
        }
        if (commandLength == "event".length()) {
            // Empty event command given (e.g. "event")
            throw new DukeException(errorMessage);
        }
        if (!command.contains(eventDelimiter)) {
            throw new DukeException(errorMessage);
        }
    }

    /**
     * Throws duke.exception.DukeException if deadline command has any invalid parameters.
     *
     * @throws DukeException for invalid deadline command
     */
    public static void verifyDeadlineInput(String command, String[] commandWords) throws DukeException {
        String deadlineDelimiter = commandDelimiter.get(commandWords[0]);
        int deadlineDelimiterIndex = command.indexOf(deadlineDelimiter);
        int deadlineDelimiterLength = deadlineDelimiter.length();
        int commandLength = command.length();
        String errorMessage = commandTypeFormatInfo.get("deadline");
        int commandWordCount = commandWords.length;

        if (deadlineDelimiterIndex + deadlineDelimiterLength == commandLength) {
            // Delimiter is at the end of command (e.g. "deadline /by")
            throw new DukeException(errorMessage);
        }
        if (commandWordCount < 4) {
            throw new DukeException(errorMessage);
        }
        if (commandLength == "deadline".length()) {
            // Empty deadline command given (e.g. "deadline")
            throw new DukeException(errorMessage);
        }
        if (!command.contains(deadlineDelimiter)) {
            throw new DukeException(errorMessage);
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
            // Check if required delimiter exists and there are at least 3 arguments
            if (commandDelimiter.containsKey(commandType) && commandWords.length >= 4) {
                String delimiter = commandDelimiter.get(commandType);
                int delimiterIndex = command.indexOf(delimiter);
                // Account for space after command and before delimiter
                description = command.substring(commandType.length() + 1, delimiterIndex - 1);
            } else {
                // Invalid command format given
                throw new DukeException(commandTypeFormatInfo.get(commandType));
            }
        }
        return description;
    }

    private static HashMap<String, String> setupCommandTypeFormatInfo() {
        HashMap<String, String> commandTypeFormatInfo = new HashMap<>();
        commandTypeFormatInfo.put("event", "Incorrect event format given... Correct format: event "
                + "[description] /at [event time in yyyy-mm-dd]");
        commandTypeFormatInfo.put("deadline", "Incorrect deadline task format given... Correct "
                + "format: deadline [description] /by "
                + "[due date in yyyy-mm-dd]");
        commandTypeFormatInfo.put("todo", "Incorrect todo task format given... Correct format: todo "
                + "[description]");
        commandTypeFormatInfo.put("find", "Incorrect find task format given... Correct format: "
                + "find [search term]");
        commandTypeFormatInfo.put("unknown", DukeException.exceptionIcon
                + " OOPS!!! I'm sorry, but I don't know what that means :-(");
        commandTypeFormatInfo.put("missingTaskNumber", "Invalid Task Number given!");
        return commandTypeFormatInfo;
    }

    private static HashMap<String, String> setupCommandDelimiter() {
        HashMap<String, String> commandDelimiter = new HashMap<>();
        commandDelimiter.put("deadline", "/by");
        commandDelimiter.put("event", "/at");
        return commandDelimiter;
    }
}
