package resources;
/**
 * DateTimeHandler acts as a wrapper class for functions
 * to getLocalDate time using only
 * descriptions
 * It also handles anything regarding datetime
 */

import duke.UI;
import exceptions.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class DateTimeHandler {
    /**
     * Gets local date time.
     *
     * @param desc the desc
     * @return the local date time
     * @throws DukeException the duke exception
     */
    public static LocalDateTime getLocalDateTime(String desc) throws DukeException {
        try {
            if (containsTime(desc)) {
                String[] splitted = desc.split(" ");
                return DateTime.getDateTime(splitted[0], splitted[1]);
            } else if (isValidDate(desc)) {
                return DateTime.getDate(desc);
            } else if (isValidTime(desc)) {
                return DateTime.getTime(desc);
            } else {
                throw new DukeException(UI.getReply("wrongDate"));
            }
        } catch (DateTimeException e) {
            throw new DukeException(UI.getReply("wrongDateFormat"));
        }
    }

    /**
     * Prints local date time string.
     *
     * @param dateTime the date time
     * @return the string
     */
    public static String printsLocalDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        } else if (dateTime.getHour() == 0 && dateTime.getMinute() == 0 && dateTime.getNano() == 0) { //only date
            return printDate(dateTime);
        } else {
            return printDateAndTime(dateTime);
        }
    }

    /**
     * Checks if given string contains time format
     *
     * @param desc description
     * @return boolean value if description contains time syntax
     */
    private static boolean containsTime(String desc) {
        String pattern = "[0-9]{2}/[0-9]{2}/[0-9]{4} [0-9]{4}";
        String patternEng = "[0-9]{2} [a-zA-Z]{3} [0-9]{4} [0-9]{2}:[0-9]{2}[apmAPM]{2}";
        String patternEng2 = "[0-9]{1} [a-zA-Z]{3} [0-9]{4} [0-9]{2}:[0-9]{2}[apmAPM]{2}";
        return Pattern.matches(pattern, desc)
                 || Pattern.matches(patternEng, desc)
                || Pattern.matches(patternEng2, desc); //i know this is bad
    }

    /**
     * Checks if given string is valid date
     *
     * @param date date in string
     * @return boolean value if string is a valid date
     */
    private static boolean isValidDate(String date) {
        String pattern = "[0-9]{2}/[0-9]{2}/[0-9]{4}";
        return Pattern.matches(pattern, date);
    }


    /**
     * Checks if given string is valid time
     *
     * @param time time in string
     * @return boolean value if string is a valid time
     */
    private static boolean isValidTime(String time) {
        String pattern = "[0-9]{4}";
        return Pattern.matches(pattern, time);
    }

    /**
     * Reformats the dateTime
     *
     * @param dateTime
     * @return String of the dateTime in correct format
     */
    private static String printDateAndTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("d MMM yyyy hh:mma");
        return dateTime.format(formatter);
    }

    private static String printDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return dateTime.format(formatter);
    }

    private static String printTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return dateTime.format(formatter);
    }

    /**
     * Reverts date time string for easier parse to memory.
     *
     * @param dateTime the date time
     * @return the string for memory storage
     */
    public static String revertDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mma");
        DateTimeFormatter formatterSave = DateTimeFormatter.ofPattern("dd/MM/yyyy hhmm");
        if (!containsTime(dateTime)) {
            formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
            formatterSave = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateTime, formatter).format(formatterSave);
        }
        return LocalDateTime.parse(dateTime, formatter).format(formatterSave);
    }

}
