package duke.core;

import duke.command.Command;
import duke.command.ListCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.UpdateDescriptionCommand;
import duke.command.UpdateTimeCommand;
import duke.command.TodoCommand;
import duke.command.EventCommand;
import duke.command.DeadlineCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.InvalidCommandException;

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
    public Command parseCommand(String input) throws InvalidCommandException {
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
            String secondCommand = split[1];
            if (secondCommand.compareTo("description") == 0) {
                result = new UpdateDescriptionCommand(input, false);
            } else if (secondCommand.compareTo("time") == 0) {
                result = new UpdateTimeCommand(input, false);
            }
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
            throw new InvalidCommandException("Sorry, invalid command. Try again with the following:\ntodo, deadline, event");
        }

        return result;
    }

    /**
     * Parses the input string into the right date and time format.
     * @param time The given user input.
     * @return The reformatted string.
     */
    public static String reformatDateAndTime(String time) {
        String result = "";
        String dateRegex = "\\d{4}-\\d{2}-\\d{2}";
        String timeRegex = "([0-1][0-9]|2[0-3])[0-5][0-9]";
        String[] split = time.split(" ");
        for (String str : split) {
            Boolean dateMatches = str.matches(dateRegex);
            Boolean timeMatches = str.matches(timeRegex);
            if (dateMatches) {
                result += parseDate(str) + " ";
            } else if (timeMatches) {
                result += parseTime(str) + " ";
            } else {
                result += str + " ";
            }
        }
        return result.trim();
    }

    private static String parseDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        String result = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return result;
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