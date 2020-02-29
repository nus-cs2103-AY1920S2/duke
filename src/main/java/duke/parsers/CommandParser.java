package duke.parsers;

import java.time.LocalDateTime;

import duke.exceptions.DukeException;

/**
 * Parses commands and breaks a command down into its command arguments.
 */
public class CommandParser {
    private DateTimeParser dateTimeParser = new DateTimeParser();

    /**
     * Separates a given string by a the first occurrence of a delimiter.
     * 
     * @param str       String to be split.
     * @param delimiter Delimiter that divides the string.
     * @return Array of the two halves of the given string split by the delimiter,
     *         or array of the original string if delimiter is not present.
     */
    public String[] splitByDelimiter(String str, String delimiter) {
        return str.split(delimiter, 2);
    }

    /**
     * Returns a LocalDateTime parsed from user-entered string. If no time is
     * specified, it is automatically set to start of day.
     * 
     * @param str User-entered string representing date/time.
     * @return LocalDateTime parsed from user input.
     * @throws DukeException If unable to parse string.
     */
    public LocalDateTime parse(String str) throws DukeException {
        return dateTimeParser.parse(str);
    }
}