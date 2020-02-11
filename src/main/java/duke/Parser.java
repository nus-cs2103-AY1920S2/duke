package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.DoneCommand;
import duke.command.SearchCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.AddTodoCommand;

import duke.exception.TimeFormatException;
import duke.exception.UnknownCommandException;
import duke.exception.MissingTimeException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingParameterException;

/** Class representing the parser with methods to parse strings. */
public class Parser {

    /**
     * Parses the user input to a Command object.
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
            if (keywords.length == 1) {
                throw new MissingParameterException("Done");
            }
            return new DoneCommand(parseStringToInt(keywords[1]));
        case "search":
            if (keywords.length == 1) {
                throw new MissingParameterException("Search");
            }
            return new SearchCommand(keywords[1]);
        case "delete":
            if (keywords.length == 1) {
                throw new MissingParameterException("Delete");
            }
            return new DeleteCommand(parseStringToInt(keywords[1]));
        case "todo":
            if (keywords.length == 1) {
                throw new MissingParameterException("Todo");
            }
            return new AddTodoCommand(keywords[1]);
        case "event":
            if (keywords.length == 1) {
                throw new MissingParameterException("Event");
            }
            return parseEvent(keywords[1]);
        case "deadline":
            if (keywords.length == 1) {
                throw new MissingParameterException("Deadline");
            }
            return parseDeadline(keywords[1]);
        default:
            throw new UnknownCommandException(keywords[0]);
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
            assert !longDescription.contains(" /by "): "Deadline has time";
            throw new MissingTimeException("Deadline");
        }
        String description = strArr[0];
        LocalDateTime time = stringToTime(strArr[1]);
        return new AddDeadlineCommand(description, time);
    }

    /**
     * Parses description into Event Command.
     * @param longDescription String description to describe the event.
     * @return EventCommand as described by the description.
     */
    public AddEventCommand parseEvent(String longDescription) {
        String[] strArr = longDescription.split(" /at ", 2);
        if (strArr.length == 1) {
            assert !longDescription.contains(" /at "): "Event has time";
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
