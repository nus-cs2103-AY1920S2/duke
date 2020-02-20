package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.command.SearchCommand;
import duke.command.UpdateDeadlineCommand;
import duke.command.UpdateEventCommand;
import duke.command.UpdateTodoCommand;
import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingParameterException;
import duke.exception.MissingTimeException;
import duke.exception.TimeFormatException;
import duke.exception.UnknownCommandException;

/**
 * Class representing the parser with methods to parse strings.
 */
public class Parser {

    /**
     * Parses the user input to a Command object.
     *
     * @param longCommand user String input.
     * @return Command object that represents the user input.
     */
    public Command parse(String longCommand) {
        String[] keywords = longCommand.split(" ", 2);
        String commandString = keywords[0];

        switch (commandString) {
        case "bye" :
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            checkKeywordsLength(keywords);
            return new DoneCommand(parseStringToInt(keywords[1]));
        case "search":
            checkKeywordsLength(keywords);
            return new SearchCommand(keywords[1]);
        case "delete":
            checkKeywordsLength(keywords);
            return new DeleteCommand(parseStringToInt(keywords[1]));
        case "todo":
            checkKeywordsLength(keywords);
            return new AddTodoCommand(keywords[1]);
        case "event":
            checkKeywordsLength(keywords);
            return parseEvent(keywords[1]);
        case "deadline":
            checkKeywordsLength(keywords);
            return parseDeadline(keywords[1]);
        case "updatetodo":
            checkKeywordsLength(keywords);
            return parseUpdateTodo(keywords[1]);
        case "updatedeadline":
            checkKeywordsLength(keywords);
            return parseUpdateDeadline(keywords[1]);
        case "updateevent":
            checkKeywordsLength(keywords);
            return parseUpdateEvent(keywords[1]);
        default:
            throw new UnknownCommandException(keywords[0]);
        }
    }

    private String[] tokenizeUpdateCommand(String description) {
        String[] tokenArr = description.split("desc/|time/");
        if (tokenArr.length < 2) {
            throw new DukeException("Wrong format for update command");
        }
        return tokenArr;
    }

    private UpdateEventCommand parseUpdateEvent(String description) {
        String[] tokenArr = tokenizeUpdateCommand(description);
        if (tokenArr.length != 3) {
            throw new DukeException("Wrong format for update event command");
        }
        String indexStr = tokenArr[0].trim();
        String newDesc = tokenArr[1].trim();
        String newTimeStr = tokenArr[2].trim();
        LocalDateTime newTime = stringToTime(newTimeStr);
        return new UpdateEventCommand(Integer.parseInt(indexStr) - 1, newDesc, newTime);
    }

    private UpdateDeadlineCommand parseUpdateDeadline(String description) {
        String[] tokenArr = tokenizeUpdateCommand(description);
        if (tokenArr.length != 3) {
            System.out.println(tokenArr.length);
            throw new DukeException("Wrong format for update deadline command");
        }
        String indexStr = tokenArr[0].trim();
        String newDesc = tokenArr[1].trim();
        String newTimeStr = tokenArr[2].trim();
        LocalDateTime newTime = stringToTime(newTimeStr);
        return new UpdateDeadlineCommand(Integer.parseInt(indexStr) - 1, newDesc, newTime);
    }

    private UpdateTodoCommand parseUpdateTodo(String description) {
        String[] tokenArr = tokenizeUpdateCommand(description);
        if (tokenArr.length != 2) {
            System.out.println(tokenArr.length);
            throw new DukeException("Wrong format for update todo command");
        }
        String indexStr = tokenArr[0].trim();
        String newDesc = tokenArr[1].trim();
        return new UpdateTodoCommand(Integer.parseInt(indexStr) - 1, newDesc);
    }

    /**
     * Checks if the keywords array is of the length 2. Throws MissingParameterException
     * if keywords array is anything but 2.
     *
     * @param keywords Array of keywords
     */
    private void checkKeywordsLength(String[] keywords) {
        if (keywords.length != 2) {
            throw new MissingParameterException(
                    keywords[0].substring(0, 1).toUpperCase() + keywords[0].substring(1));
        }
    }

    /**
     * Parses string that is supposed to represent a number to int.
     *
     * @param indexStr String that is supposed to represent an integer.
     * @return Integer parsed from the String.
     */
    public int parseStringToInt(String indexStr) {
        try {
            return Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(indexStr);
        }
    }

    /**
     * Parses description into Deadline Command.
     *
     * @param longDescription String description to describe the deadline.
     * @return DeadlineCommand as described by the description.
     */
    public AddDeadlineCommand parseDeadline(String longDescription) {
        String[] strArr = longDescription.split(" /by ", 2);
        if (strArr.length == 1) {
            assert !longDescription.contains(" /by ") : "Deadline has time";
            throw new MissingTimeException("Deadline");
        }
        String description = strArr[0];
        LocalDateTime time = stringToTime(strArr[1]);
        return new AddDeadlineCommand(description, time);
    }

    /**
     * Parses description into Event Command.
     *
     * @param longDescription String description to describe the event.
     * @return EventCommand as described by the description.
     */
    public AddEventCommand parseEvent(String longDescription) {
        String[] strArr = longDescription.split(" /at ", 2);
        if (strArr.length == 1) {
            assert !longDescription.contains(" /at ") : "Event has time";
            throw new MissingTimeException("Event");
        }
        String description = strArr[0];
        LocalDateTime time = stringToTime(strArr[1]);
        return new AddEventCommand(description, time);
    }

    /**
     * Parses time String to LocalDateTime object.
     *
     * @param str String of the time input of the user.
     * @return LocalDateTime representation of that time format.
     */
    public LocalDateTime stringToTime(String str) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(str, formatter);
        } catch (DateTimeParseException e) {
            throw new TimeFormatException();
        }
    }
}
