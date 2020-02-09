package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a time formatter which translates between forms of date representation.
 */
public class TimeFormatter {

    private String strDate;

    /**
     * Constructor of TimeFormatter class.
     *
     * @param strDate the original string of date
     */
    public TimeFormatter(String strDate) {
        this.strDate = strDate.trim();
    }

    /**
     * Checks if the input date format is valid for translation.
     *
     * @return a boolean value whether the format is valid
     */
    public boolean checkValidDateFormat() {
        if (this.strDate.trim().equals("")) {
            return false;
        }
        return Integer.parseInt(this.strDate.substring(5, 7)) <= 12;
    }

    /**
     * Converts the original date to a desired format.
     *
     * @return the string of the desired format of date
     */
    public String toDesiredFormat() {
        LocalDate localDate = LocalDate.parse(this.strDate);
        return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Processes the date object.
     *
     * @return the original or modified version of date
     */
    public String processDate() {
        if (!this.checkValidDateFormat()) {
            return this.strDate;
        } else {
            return this.toDesiredFormat();
        }
    }
}
