package duke;

import duke.enums.Command;
import duke.exception.BadDateException;
import duke.exception.InvalidCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser with static methods to interpret Strings into actionable data for DukeProject.
 */
public class Parser {
    /** Date formatter when reading (to print from Duke) the date. */
    public static DateTimeFormatter DATE_READ_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM uuuu");
    /** Date formatter when writing (to write into Duke) the date.*/
    public static DateTimeFormatter DATE_WRITE_FORMATTER =
            DateTimeFormatter.ofPattern("dd MM uu");

    /**
     * Returns the date in LocalDate form parsed from the String entered.
     * If the String format is wrong, will throw BadDateException.
     * @param str String to be parsed into a date.
     * @return date in LocalDate format.
     * @throws BadDateException if format of string is not aligned with write formatter.
     */
    public static LocalDate dateParser(String str) throws BadDateException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM uu");
            return LocalDate.parse(str, formatter);
        } catch (DateTimeParseException e) {
            throw new BadDateException("Bad date format");
        }
    }

    /**
     * Returns the command in the form of enum class Command parsed from the String entered.
     * If String does not coincide with any command, will throw InvalidCommandException.
     * @param str String to be parsed into a command.
     * @return command in enum class Command format.
     * @throws InvalidCommandException String entered does not coincide with any command in enum class Command.
     */
    public static Command commandParser(String str) throws InvalidCommandException {
        try {
            return Command.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new InvalidCommandException(str + " is an invalid command");
        }
    }
}
