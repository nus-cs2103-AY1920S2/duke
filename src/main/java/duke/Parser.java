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
import command.ScheduleCommand;

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
            case "schedule":
                try {
                    return new ScheduleCommand(Parser.formatDate(Parser.getDateAndTimeOnly(trimCommand)));
                } catch (DukeException de) {
                    return new ErrorCommand(de);
                }
            case "find":
                return new FindCommand(Parser.getSecond(trimCommand));
            case "done":
                try {
                    return new DoneCommand(Integer.valueOf(Parser.getSecond(trimCommand)));
                } catch (NumberFormatException nfe) {
                    return new ErrorCommand(new DukeException(
                            "☹ OOPS!!! The description of a done should be an integer."));
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand(new DukeException(
                            "☹ OOPS!!! The description of a done cannot be empty."));
                }
            case "delete":
                try {
                    return new DeleteCommand(Integer.valueOf(Parser.getSecond(trimCommand)));
                } catch (NumberFormatException nfe) {
                    return new ErrorCommand(new DukeException(
                            "☹ OOPS!!! The description of a delete should be an integer."));
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
                    return new AddDeadlineCommand(Parser.getTaskName(trimCommand),
                            Parser.formatDateAndTime(Parser.getDateAndTimeOnly(trimCommand)));
                } catch (DukeException de) {
                    return new ErrorCommand(de);
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand(new DukeException(
                            "☹ OOPS!!! The description of a deadline have to include a name, date and time of the deadline task."));
                }
            case "event":
                try {
                    return new AddEventCommand(Parser.getTaskName(trimCommand),
                            Parser.formatDateAndTime(Parser.getDateAndTimeOnly(trimCommand)));
                } catch (DukeException de) {
                    return new ErrorCommand(de);
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand(new DukeException(
                            "☹ OOPS!!! The description of an event have to include a name, date and time of the event task."));
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
        String taskType = message.trim().split(" ")[0];
        if (hasSeparator(message)) {
            assert taskType.equals("deadline") || taskType.equals("event");
            return getBeforeSeparator(message);
        } else {
            if (taskType.equals("todo")) {
                return message.trim().substring(5);
            } else {
                throw new IndexOutOfBoundsException();
            }
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
    public static boolean hasSeparator(String message) {
        return message.contains("/");
    }

    /**
     * Retrieves the string of date and time part of a Deadline and Event only.
     * @param message The message to be sliced.
     * @return Boolean value whether text has a / separator.
     **/
    public static String getDateAndTimeOnly (String message) {
        String beforeDate = message.substring(message.indexOf("/"), message.indexOf("/") + 4);
        assert beforeDate.equals("/by ") || beforeDate.equals("/at ");
        return message.substring(message.indexOf("/") + 4);
    }

    /**
     * Retrieves the date and time format of the command.
     * @param message The message text.
     * @return Date format of the of the command of the task
     **/
    public static LocalDateTime formatDateAndTime(String message) throws DukeException {
        //String time = getTime(message);
        if (isDateValid(message) && isTimeValid(message)) {
            String time = getTime(message);
            return extractDate(message).atTime(LocalTime.parse(time));
        } else {
            throw new DukeException("☹ OOPS!!! Sorry, but your date and time format needs to be dd/mm/yy hhmm");
        }
    }

    /**
     * Retrieves the date format of the command.
     * @param message The message text.
     * @return Date format of the of the command of the task
     **/
    public static LocalDate formatDate (String message) throws DukeException {
        if (isDateValid(message)) {
            return extractDate(message);
        } else {
            throw new DukeException("☹ OOPS!!! Sorry, but your date format needs to be dd/mm/yy");
        }
    }

    /**
     * Converts a date in the form of a string to a Date form.
     * @param message The message text.
     * @return Date format of the of the string format of the date
     **/
    public static LocalDate extractDate(String message) throws DukeException {
        String[] original = getDayMonthYearArray(message);
        String[] reversed = getYearMonthDayArray(original);
        String date = String.join("-", reversed);
        return LocalDate.parse(date);
    }

    private static boolean isDateValid (String message) {
        String[] original = getDayMonthYearArray(message);
        if (original.length != 3) {
            return false;
        }
        String day = original[0];
        String month = original[1];
        String year = original[2];
        Integer intDay;
        Integer intMonth;
        Integer intYear;
        try {
            intDay = Integer.valueOf(day);
            intMonth = Integer.valueOf(month);
            intYear = Integer.valueOf(year);
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (day.length() > 2 || intDay > 31 || intDay < 1) {
            return false;
        } else if (month.length() > 2 || intMonth > 12 || intMonth < 1) {
            return false;
        } else if (year.length() > 4 || intYear < 0) {
            return false;
        }
        return true;
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
     * Checks whether the time from the message time is valid.
     * @param message The message string time.
     */
    private static boolean isTimeValid (String message) {
        try {
            if ((!message.contains(" "))) {
                return false;
            }
            String stringAssumedTime = message.substring(message.indexOf(" ") + 1);
            Integer intAssumedTime = Integer.valueOf(stringAssumedTime);
            if (stringAssumedTime.length() != 4) {
                return false;
            } else if (intAssumedTime < 0 || intAssumedTime > 2400) {
                    return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Splits a string of date into a day, month, year array.
     * @param message The message text.
     * @return String array of day, month, year of the message date.
     **/
    private static String[] getDayMonthYearArray(String message) {
        if (message.contains(" ")) {
            return message.substring(0, message.indexOf(" "))
                    .split("/");
        }
        return message.split("/");
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
