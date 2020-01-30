package duke.util;

import duke.exception.DukeInvalidDateFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/*
 * TaskSchedule
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 27 Jan 2020
 *
 */

/**
 * <p>TaskSchedule abstracts the date of an event and
 * a deadline, with a properly formatted input and output.</p>
 * @author Mario Lorenzo
 */

public class TaskSchedule {
    private LocalDateTime date;
    private static final String INPUT_DATE_FORMAT = "dd-MM-yyyy HH:mm";
    private static final String OUTPUT_DATE_FORMAT = "dd-MM-yyyy HH:mm";

    /**
     * Constructs a TaskSchedule instance.
     * @param date The verified date of an event or deadline.
     */

    private TaskSchedule(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Returns a TaskSchedule instance in a factory method fashion
     * with validating the input date string.
     * @param scheduleString The String that represents the date of an event or deadline.
     * @return The corresponding TaskSchedule instance.
     * @throws DukeInvalidDateFormatException If the date string is not properly formatted.
     */

    public static TaskSchedule parseSchedule(String scheduleString) throws DukeInvalidDateFormatException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);
            LocalDateTime date = LocalDateTime.parse(scheduleString, formatter);
            return new TaskSchedule(date);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateFormatException("OOPS! It seems that your date "
                    + "is not properly formatted. The date should be in form of 'dd-MM-yyyy HH:mm'");
        }
    }

    /**
     * Gets the date in form of LocalDateTime.
     * @return The LocalDateTime instance.
     */

    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Overrides the toString method to print
     * the formatted date using DateTimeFormatter instance.
     * @return The formatted String representing the date
     */

    @Override
    public String toString() {
        return this.date.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT));
    }
}
