package duke.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeParser {

    private SimpleDateFormat timeFormat;
    private SimpleDateFormat dateFormat;

    public DateTimeParser() {
        this.timeFormat = new SimpleDateFormat("HH:mm");
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * Parses date from the string entered by user
     *
     * @param dateTime user input containing date information
     * @return Date object parsed from input
     */
    public Date parseDate(String dateTime) throws ParseException {
        if (dateTime.indexOf('-') != -1) {
            return dateFormat.parse(dateTime);
        }
        return null;
    }

    /**
     * Parses time from the string entered by user
     *
     * @param dateTime user input containing time information
     * @return Date object parsed from input
     *
     */
    public Date parseTime(String dateTime) throws ParseException {
        int breakPoint = dateTime.indexOf(':');
        if(breakPoint != -1) {
            return timeFormat.parse(dateTime.substring(breakPoint - 2));
        }
        return null;
    }
}
