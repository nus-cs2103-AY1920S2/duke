package duke.common;

import java.time.format.DateTimeFormatter;

/**
 * Container for Date and Time format using java.time.format.DateTimeFormatter.
 */
public class DateTimeFormat {
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
    public static final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");
}
