package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The type Date and time.
 */
public class DateAndTime {

    private LocalDate date;
    private Ui ui = new Ui();

    /**
     * Instantiates a new Date and time.
     *
     * @param dateInString the date in string
     */
    public DateAndTime(String dateInString) throws DukeException {
        try {
            this.date = LocalDate.parse(dateInString);
        } catch (DateTimeParseException e) {
            throw new DukeException(ui.showWrongDateException());
        }
    }

    /**
     * Formats date to string.
     *
     * @return the string
     */
    public String formatDateToString() {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

}
