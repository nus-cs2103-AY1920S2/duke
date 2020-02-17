package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exception.IncorrectCommandException;

import static duke.common.DateTimeFormat.INPUT_DATE_TIME_FORMAT;
import static duke.common.Messages.EMPTY_DESCRIPTION_ERROR_MSG;
import static duke.common.Messages.EMPTY_TIME_ERROR_MSG;
import static duke.common.Messages.INVALID_COMMAND_ERROR_MSG;
import static duke.common.Messages.INVALID_INDEX_ERROR_MSG;
import static duke.common.Messages.generateAddDeadlineErrorMessage;
import static duke.common.Messages.generateAddEventErrorMessage;
import static duke.common.Messages.generateAddTodoErrorMessage;
import static duke.common.Messages.generateDeleteErrorMessage;
import static duke.common.Messages.generateDoneErrorMessage;
import static duke.common.Messages.generateFindErrorMessage;
import static duke.common.Messages.generateTimeFormatErrorMessage;


/**
 * Handles the parsing of the user's input command.
 */
public class CommandParser {
    /**
     * Returns the corresponding Command to be executed after parsing the specified user input command.
     *
     * @param userInputCommand The specified user input command.
     * @return The corresponding Command to be executed.
     * @throws IncorrectCommandException If the user inputs an invalid or incomplete command.
     */
    public static Command parse(String userInputCommand) throws IncorrectCommandException {
        String[] tokens = userInputCommand.split("\\s+");
        String commandWord = tokens[0];

        if (commandWord.equals("done")) {
            // Mark as done command
            if (tokens.length != 2) {
                // Command is invalid if the number of arguments not exactly 1.
                throw new IncorrectCommandException(generateDoneErrorMessage(INVALID_INDEX_ERROR_MSG));
            }
            try {
                int taskIndex = Integer.parseInt(tokens[1]) - 1; // May throw NumberFormatException
                return new MarkAsDoneCommand(taskIndex);
            } catch (NumberFormatException e) {
                // Command is invalid if the argument is not a valid integer.
                throw new IncorrectCommandException(generateDoneErrorMessage(INVALID_INDEX_ERROR_MSG));
            }
        } else if (commandWord.equals("delete")) {
            // Delete command
            if (tokens.length != 2) {
                // Command is invalid if the number of arguments not exactly 1.
                throw new IncorrectCommandException(generateDeleteErrorMessage(INVALID_INDEX_ERROR_MSG));
            }
            try {
                int taskIndex = Integer.parseInt(tokens[1]) - 1; // May throw NumberFormatException
                return new DeleteCommand(taskIndex);
            } catch (NumberFormatException e) {
                // Command is invalid if the argument is not a valid integer.
                throw new IncorrectCommandException(generateDeleteErrorMessage(INVALID_INDEX_ERROR_MSG));
            }
        } else if (commandWord.equals("find")) {
            // Find command
            if (tokens.length != 2) {
                // Command is invalid if the number of arguments not exactly 1.
                throw new IncorrectCommandException(generateFindErrorMessage());
            }
            return new FindCommand(tokens[1]);
        } else if (commandWord.equals("todo")
                || commandWord.equals("deadline")
                || commandWord.equals("event")) {
            // Add command
            return parseAddCommandFromTokens(tokens);
        } else if (userInputCommand.equals("list")) {
            // List command
            return new ListCommand();
        } else if (userInputCommand.equals("undo")) {
            // Undo command
            return new UndoCommand();
        } else if (userInputCommand.equals("bye")) {
            // Exit command
            return new ExitCommand();
        } else {
            // Invalid commands
            throw new IncorrectCommandException(INVALID_COMMAND_ERROR_MSG);
        }
    }


    private static AddCommand parseAddCommandFromTokens(String[] tokens) throws IncorrectCommandException {
        String keyword = tokens[0];
        StringBuilder descriptionBuilder = new StringBuilder();
        StringBuilder timeBuilder = new StringBuilder();
        boolean isDescDone = false;
        String delimiter;
        switch (keyword) {
        case "deadline":
            delimiter = "/by";
            break;
        case "event":
            delimiter = "/at";
            break;
        default:
            delimiter = "";
        }

        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].equals(delimiter)) {
                isDescDone = true;
            } else if (isDescDone) {
                timeBuilder.append(tokens[i]).append(' ');
            } else {
                descriptionBuilder.append(tokens[i]).append(' ');
            }
        }

        String description = descriptionBuilder.toString().stripTrailing();
        String timeString = timeBuilder.toString().stripTrailing();

        switch (keyword) {
        case "todo":
            if (description.isBlank()) {
                throw new IncorrectCommandException(generateAddTodoErrorMessage(EMPTY_DESCRIPTION_ERROR_MSG));
            }
            return new AddCommand(AddCommand.TYPE_TODO, description, null);
        case "deadline":
            if (description.isBlank()) {
                throw new IncorrectCommandException(generateAddDeadlineErrorMessage(EMPTY_DESCRIPTION_ERROR_MSG));
            } else if (timeString.isBlank()) {
                throw new IncorrectCommandException(generateAddDeadlineErrorMessage(EMPTY_TIME_ERROR_MSG));
            }

            try {
                LocalDateTime time = LocalDateTime.parse(timeString, INPUT_DATE_TIME_FORMAT);
                return new AddCommand(AddCommand.TYPE_DEADLINE, description, time);
            } catch (DateTimeParseException e) {
                throw new IncorrectCommandException(generateTimeFormatErrorMessage());
            }
        case "event":
            if (description.isBlank()) {
                throw new IncorrectCommandException(generateAddEventErrorMessage(EMPTY_DESCRIPTION_ERROR_MSG));
            } else if (timeString.isBlank()) {
                throw new IncorrectCommandException(generateAddEventErrorMessage(EMPTY_TIME_ERROR_MSG));
            }

            try {
                LocalDateTime time = LocalDateTime.parse(timeString, INPUT_DATE_TIME_FORMAT);
                return new AddCommand(AddCommand.TYPE_EVENT, description, time);
            } catch (DateTimeParseException e) {
                throw new IncorrectCommandException(generateTimeFormatErrorMessage());
            }
        default: // Never reached
            throw new AssertionError("Invalid commands have already been accounted for.");
        }
    }
}
