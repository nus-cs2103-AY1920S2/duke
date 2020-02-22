package duke.temporal;

import duke.exception.InvalidDateFormatException;
import duke.exception.InvalidTimeFormatException;

import java.security.PrivilegedExceptionAction;

public class DateTimeParser {
    public static final String DATE_FORMAT_ERROR_MESSAGE = "HEY!!! The date format you entered is invalid!\n";
    public static final String TIME_FORMAT_ERROR_MESSAGE = "HEY!!! The time format you entered is invalid!\n";

    /**
     * Take in the raw date and time and returns a date string parsable by LocalDate.
     *
     * @param rawDateTime Raw date and time string of format dd/MM/yyyy hhmm.
     * @return date string parsable by LocalDate.
     * @throws InvalidDateFormatException if the date format is invalid.
     */
    public static String getParsableDate(String rawDateTime) throws InvalidDateFormatException {
        try {
            String[] separatedDateTime = rawDateTime.split(" ");
            String[] date = separatedDateTime[0].split("/");
            if (date[0].length() < 2) {
                date[0] = "0" + date[0];
            }
            if (date[1].length() < 2) {
                date[1] = "0" + date[1];
            }
            String formattedDate = date[2] + "-" + date[1] + "-" + date[0];
            return formattedDate;
        } catch (Exception e) {
            throw new InvalidDateFormatException(DATE_FORMAT_ERROR_MESSAGE);
        }
    }

    /**
     * Takes in the raw date and time and returns a time string parsable by LocalTime.
     *
     * @param rawDateTime Raw date and time string of format dd/MM/yyyy hhmm.
     * @return time string parsable by LocalTime.
     * @throws InvalidTimeFormatException if the time format is invalid.
     */
    public static String getParsableTime(String rawDateTime) throws InvalidTimeFormatException {
        try {
            String[] separatedDateTime = rawDateTime.split(" ");
            if (separatedDateTime[1].length() < 4) {
                separatedDateTime[1] = "0" + separatedDateTime[1];
            }
            String hour = separatedDateTime[1].substring(0, 2);
            if (hour.equals("24")) {
                hour = "00";
            }
            String minute = separatedDateTime[1].substring(2, 4);
            String formattedTime = hour + ":" + minute + ":00";
            return formattedTime;
        } catch (Exception e) {
            throw new InvalidTimeFormatException(TIME_FORMAT_ERROR_MESSAGE);
        }
    }
}
