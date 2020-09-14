package dukeproj;

import dukeproj.command.*;
import dukeproj.enums.CommandType;
import dukeproj.exception.BadDateException;
import dukeproj.exception.InvalidCommandException;

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
     *
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
     *
     * @param str String to be parsed into a command.
     * @return command in enum class Command format.
     * @throws InvalidCommandException String entered does not coincide with any command in enum class Command.
     */
    public static CommandType commandParser(String str) throws InvalidCommandException {
        try {
            return CommandType.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException(str + " is an invalid command");
        }
    }

    /**
     * Gets a Command class object from a certain command type and description.
     *
     * @param commandType The type of command to return.
     * @param description The description accompanying the command.
     * @return The Command class object to be returned.
     */
    public Command getCommand(CommandType commandType, String description) {
        Command command = new ExitCommand(); //default command
        switch (commandType) {
        case LIST:
            command = new ListCommand();
            break;
        case DONE:
            command = new DoneCommand(description);
            break;
        case TODO:
            //fallthrough
        case EVENT:
            //fallthrough
        case DEADLINE:
            command = new AddCommand(description, commandType);
            break;
        case DELETE:
            command = new DeleteCommand(description);
            break;
        case SCHEDULE:
            command = new ScheduleCommand(description);
            break;
        case FIND:
            command = new FindCommand(description);
            break;
        case HELP:
            command = new HelpCommand();
            break;
        case BYE:
            //fallthrough
        default:
            assert !commandType.equals(CommandType.BYE) : "Erroneous Command";
            break;
        }
        return command;
    }

    /**
     * Constructs an empty Parser to be used in Duke GUI version.
     */
    public Parser() {
    }
}
