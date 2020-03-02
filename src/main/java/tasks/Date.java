package tasks;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static commons.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents Date in a Deadline or Event task.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}.
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should only be in the format YYYY-MM-DD, and it should not be blank";

    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
    }

    /**
     * Checks if a given string is a valid date.
     *
     * @return true if the string matches date format.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns date as a string with format MMM d yyyy.
     *
     * @return date as a string with format MMM d yyyy.
     */
    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}

