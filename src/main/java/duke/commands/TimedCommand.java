package duke.commands;

import java.time.LocalDateTime;

import duke.parsers.DateTimeParser;
import duke.exceptions.DukeException;

/**
 * Represents a command that requires parsing of DateTime information.
 */
public abstract class TimedCommand implements Command {
    DateTimeParser dtParser;

    public TimedCommand(DateTimeParser dtParser) {
        this.dtParser = dtParser;
    }

    /**
     * Converts a user-entered date/time string into LocalDateTime.
     * 
     * @param dateTime User-entered string representing date/time.
     * @return LocalDateTime parsed from user input.
     * @throws DukeException If unable to parse string.
     */
    public LocalDateTime parseDateTime(String dateTime) throws DukeException {
        return dtParser.parse(dateTime);
    }
}
