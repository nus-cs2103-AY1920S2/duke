package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

public class StringParser extends Parser {
    /**
     * Parses an integer from a string.
     *
     * @param input a string to convert into an integer.
     * @return the integer representation of the string.
     * @throws DukeException if input is not an integer.
     */
    public static int parseInt(String input) throws DukeException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Input was not an integer
            throw new DukeException("That's clearly not a number.");
        }
    }

    /**
     * Parses a boolean from a string.
     *
     * @param input a string to convert into a boolean.
     * @param trueInput the string representation if the input is true.
     * @param falseInput the string representation if the input is false.
     * @return the boolean representation of the string.
     * @throws DukeException if input cannot be parsed as a boolean.
     */
    public static boolean parseBoolean(String input, String trueInput, String falseInput) throws DukeException {
        if (input.equals(trueInput)) {
            return true;
        } else if (input.equals(falseInput)) {
            return false;
        } else {
            String error = String.format("It's %s (for true) or %s (for false). This isn't a boolean.", trueInput, falseInput);
            throw new DukeException(error);
        }
    }

    /**
     * Parses a date from a string.
     *
     * @param input a string to convert into a date, in yyyy-mm-dd format.
     * @return the date representation of the string.
     * @throws DukeException if input cannot be parsed as a date object.
     */
    public static LocalDate parseDate(String input) throws DukeException {
        try {
            return LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);

        } catch (DateTimeParseException e) {
            throw new DukeException("I need the date in this format:\n"
                    + " yyyy-mm-dd.");
        }
    }
}
