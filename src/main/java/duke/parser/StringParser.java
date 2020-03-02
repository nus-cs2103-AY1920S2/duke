package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.parser.exception.DateFormatException;
import duke.parser.exception.ParseException;
import duke.parser.exception.TimeFormatException;
import duke.util.DateTimeUtil;

/**
 * Represents a Parser that parses user input into common values.
 */
public class StringParser extends Parser {
    /**
     * Parses an integer from a string.
     *
     * @param input a string to convert into an integer.
     * @return the integer representation of the string.
     * @throws ParseException if input is not an integer.
     */
    public static int parseInt(String input) throws ParseException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Input was not an integer
            throw new ParseException("That's clearly not a number.");
        }
    }

    /**
     * Parses a boolean from a string.
     *
     * @param input a string to convert into a boolean.
     * @param trueInput the string representation if the input is true.
     * @param falseInput the string representation if the input is false.
     * @return the boolean representation of the string.
     * @throws ParseException if input cannot be parsed as a boolean.
     */
    public static boolean parseBoolean(String input, String trueInput, String falseInput)
            throws ParseException {
        if (input.equals(trueInput)) {
            return true;
        } else if (input.equals(falseInput)) {
            return false;
        } else {
            String error = String.format("It's %s (for true) or %s (for false)."
                    + " This isn't a boolean.", trueInput, falseInput);
            throw new ParseException(error);
        }
    }

    /**
     * Parses a date from a string.
     *
     * @param input a string to convert into a date, in yyyy-MM-dd format.
     * @return the date representation of the string.
     * @throws DateFormatException if input cannot be parsed as a date object.
     */
    public static LocalDate parseDate(String input) throws DateFormatException {
        try {
            return LocalDate.parse(input, DateTimeUtil.FORMAT_DATE_NUMERIC);

        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    /**
     * Parses a 24-hour time from a string.
     *
     * @param input a string to convert into a time, in HH:mm format.
     * @return the time representation of the string.
     * @throws TimeFormatException if input cannot be parsed as a time object.
     */
    public static LocalTime parseTime(String input) throws TimeFormatException {
        try {
            return LocalTime.parse(input, DateTimeUtil.FORMAT_TIME_24H);

        } catch (DateTimeParseException e) {
            throw new TimeFormatException();
        }
    }
}
