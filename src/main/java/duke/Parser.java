package duke;

import command.Command;
import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddToDoCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.ErrorCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This class takes care to interpret information from user's input.
 **/
public class Parser {

    /**
     * Retrieves the command object to be generated
     * according to user's input.
     * @return Command object of the input from the user.
     **/
    public static Command parse(String command) {
        String trimCommand = command.trim();
        String firstCommand;
        if (command.trim().contains(" ")) {
            firstCommand = trimCommand.split(" ")[0];
        } else {
            firstCommand = command.trim();
        }
        switch (firstCommand) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "find":
                return new FindCommand(Parser.getSecond(trimCommand));
            case "done":
                try {
                    return new DoneCommand(Integer.valueOf(Parser.getSecond(trimCommand)));
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand(new DukeException(
                            "☹ OOPS!!! The description of a done cannot be empty."));
                }
            case "delete":
                try {
                    return new DeleteCommand(Integer.valueOf(Parser.getSecond(trimCommand)));
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand(new DukeException(
                            "☹ OOPS!!! The description of a delete cannot be empty."));
                }
            case "todo":
                try {
                    return new AddToDoCommand(Parser.getTaskName(trimCommand));
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand(new DukeException(
                            "☹ OOPS!!! The description of a todo cannot be empty."));
                }
            case "deadline":
                try {
                    return new AddDeadlineCommand(Parser.getTaskName(trimCommand), Parser.getDate(trimCommand));
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand(new DukeException(
                            "☹ OOPS!!! The description of a deadline cannot be empty."));
                }
            case "event":
                try {
                    return new AddEventCommand(Parser.getTaskName(trimCommand), Parser.getDate(trimCommand));
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand(new DukeException(
                            "☹ OOPS!!! The description of an event cannot be empty."));
                }
            default:
                return new ErrorCommand(new DukeException(
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
        }
    }

    /**
     * Retrieves the second argument of the command.
     * @param message The command text.
     * @return String of the second argument of the command
     **/
    public static String getSecond(String message) {
        return message.trim().split(" ")[1];
    }

    /**
     * Retrieves the third argument of the command.
     * @param message The command text.
     * @return String of the third argument of the command
     **/
    public static String getTaskName(String message) {
        if (hasSeparator(message)) {
            assert taskType.equals("deadline") || taskType.equals("event");
            return getBeforeSeparator(message);
        } else {
            assert taskType.equals("todo") : taskType;
            return message.trim().substring(5);
        }
    }

    /**
     * Cuts the string starting from the first space until the / separator.
     * @param message The command text.
     * @return Sliced string of the text
     **/
    private static String getBeforeSeparator(String message) {
        return message.substring(message.indexOf(" ") + 1,
                message.indexOf("/") - 1);
    }

    /**
     * Checks whether the command has a / separator.
     * @param message The command text.
     * @return Boolean value whether text has a / separator.
     **/
    private static boolean hasSeparator(String message) {
        return message.contains("/");
    }

    /**
     * Retrieves the date format of the command.
     * @param message The message text.
     * @return Date format of the of the command of the task
     **/
    public static LocalDateTime getDate(String message) {
        String beforeDate = message.substring(message.indexOf("/") + 1, message.indexOf("/") + 4);
        assert beforeDate.equals("/by ");
        return extractDate(message.substring(message.indexOf("/") + 4));
    }

    /**
     * Converts a date in the form of a string to a Date form.
     * @param message The message text.
     * @return Date format of the of the string format of the date
     **/
    public static LocalDateTime extractDate(String message) {
        String time = getTime(message);
        String[] original = getDayMonthYearArray(message);
        String[] reversed = getYearMonthDayArray(original);
        String date = String.join("-", reversed);
        return LocalDate.parse(date)
                .atTime(LocalTime.parse(time));
    }

    /**
     * Obtains the time stamp from the message time.
     * @param message The message string time.
     * @return The string representing the time stamp.
     */
    private static String getTime(String message) {
        StringBuilder time = new StringBuilder(message.substring(message.indexOf(" ") + 1));
        time.insert(2, ':');
        return time.toString();
    }

    /**
     * Splits a string of date into a day, month, year array.
     * @param message The message text.
     * @return String array of day, month, year of the message date.
     **/
    private static String[] getDayMonthYearArray(String message) {
        return message.substring(0, message.indexOf(" "))
                .split("/");
    }

    /**
     * Reverses a day, month, year array to a year, month, day array.
     * @param original The day, month, year array to be reversed.
     * @return String array of year, month, day of the message date.
     **/
    private static String[] getYearMonthDayArray(String[] original) {
        String[] reversed = new String[3];
        String year = String.format("%4s",original[2]).replace(" ", "0");
        String day = String.format("%2s",original[0]).replace(" ", "0");
        String month = String.format("%2s", original[1]).replace(" ", "0");
        reversed[0] = year;
        reversed[1] = month;
        reversed[2] = day;
        return reversed;
    }
}
