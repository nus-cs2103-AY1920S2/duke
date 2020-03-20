package duke.util;

import java.time.format.DateTimeFormatter;

/**
 * Contains a collection of formatting styles for dates and times.
 */
public class DateTimeUtil {
    /** Formats a date to show the first three letters of a month name. */
    public static final DateTimeFormatter FORMAT_DATE_MONTHNAME =
            DateTimeFormatter.ofPattern("MMM d yyyy");

    /** Formats a date as numbers separated by dashes. */
    public static final DateTimeFormatter FORMAT_DATE_NUMERIC =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Formats a time to be in 24-hour format. */
    public static final DateTimeFormatter FORMAT_TIME_24H =
            DateTimeFormatter.ofPattern("HH:mm");
}
