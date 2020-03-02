package duke.core;

import duke.command.Command;
import duke.command.ListCommand;
import duke.command.ByeCommand;
import duke.command.HelpCommand;

import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;

import duke.command.UpdateDescriptionCommand;
import duke.command.UpdateTimeCommand;

import duke.command.TodoCommand;
import duke.command.EventCommand;
import duke.command.DeadlineCommand;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidTimeFormatException;
import duke.exception.ReservedSymbolException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser to parse commands from input strings.
 */
public class Parser {
    /**
     * Constructs a fresh Parser instance.
     */
    public Parser() {
    }

    /**
     * Parses the input string into a command.
     * @param input The given user input.
     * @param command The command to check against.
     * @return
     */
    public Command parseCommand(String input) throws ReservedSymbolException, InvalidCommandException {
        if (input.contains("~")) {
            throw new ReservedSymbolException(Message.RESERVED_SYMBOL_ERROR);
        }

        Command result = new ByeCommand(input, true);
        String[] split = input.split(" ");
        String command = split[0];

        assert split.length > 1 : "User input minimally must consist of one command and another argument";

        switch (command) {
        case "list":
            result = new ListCommand(input, false);
            break;
        
        case "bye":
            result = new ByeCommand(input, true);
            break;

        case "help":
            result = new HelpCommand(input, false);
            break;

        case "delete":
            result = new DeleteCommand(input, false);
            break;

        case "done":
            result = new DoneCommand(input, false);
            break;

        case "find":
            result = new FindCommand(input, false);
            break;

        case "update" :
            result = parseUpdateCommand(split, input);
            break;

        case "todo":
            result = new TodoCommand(input, false);
            break;

        case "event":
            result = new EventCommand(input, false);
            break;

        case "deadline":
            result = new DeadlineCommand(input, false);
            break;

        default:
            throw new InvalidCommandException(Message.COMMAND_ERROR);
        }

        return result;
    }

    private Command parseUpdateCommand(String[] split, String input) throws InvalidCommandException {
        try {
            String secondCommand = split[1];
            if (secondCommand.compareTo("description") == 0) {
                return new UpdateDescriptionCommand(input, false);
            } else if (secondCommand.compareTo("time") == 0) {
                return new UpdateTimeCommand(input, false);
            } else {
                throw new InvalidCommandException(Message.COMMAND_ERROR);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(Message.UPDATE_GENERAL_ERROR);
        }
    }

    /**
     * Parses the input string into the right date and time format.
     * @param time The given user input.
     * @return The reformatted string.
     */
    public static String reformatDateAndTime(String time) throws InvalidTimeFormatException {
        String result = "";
        String dateRegex = "\\d{4}-\\d{2}-\\d{2}";
        String timeRegex = "([0-1][0-9]|2[0-3])[0-5][0-9]";
        String[] split = time.trim().split(" ");
        if (split.length != 2) {
            throw new InvalidTimeFormatException(Message.TIME_ERROR);
        }

        Boolean dateMatches = split[0].matches(dateRegex);
        Boolean timeMatches = split[1].matches(timeRegex);
        if (dateMatches && timeMatches) {
            result += parseDate(split[0]) + " ";
            result += parseTime(split[1]);
        } else {
            throw new InvalidTimeFormatException(Message.TIME_ERROR);
        }
        return result;
    }

    private static String parseDate(String date) throws InvalidTimeFormatException {
        try {
            LocalDate localDate = LocalDate.parse(date);
            String result = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return result;
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException(Message.TIME_ERROR);
        }
    }

    private static String parseTime(String time) {
        String result = "";
        int hour = Integer.parseInt(time.substring(0,2));
        String minutes = time.substring(2);
        if (hour <= 12) {
            result += hour;
            result += ":" + minutes;
        } else {
            result += hour - 12;
            result += ":" + minutes;
        }

        result += hour < 12 ? "am" : "pm";
        return result;
    } 
}