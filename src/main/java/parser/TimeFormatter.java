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
     * Parses time string to a specific format.
     * @Return a string representing a formatted LocalDate
     */
    public String parseToFormat() {
        try {
            LocalDate thisLocalDate = LocalDate.parse(strDate);
            return thisLocalDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (Exception exo) {
            return this.strDate;
        }
    }
}
