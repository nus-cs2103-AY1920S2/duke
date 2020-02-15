import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Event handles event task for Duke.
 */
public class Event extends Task {
    protected String by;
    String dateAsString = "";
    LocalDate date;
    String statement = "";

    /**
     * Constructs an Event object for task added with event type.
     * @param description Description of event task.
     * @param by Due date/string.
     * @throws DateTimeParseException Parse the date if the given format is correct, else will parse the string.
     */
    public Event(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = by;
        String[] tmp = this.by.split(" ");
        statement = tmp[0];
        dateAsString = dateAsString + tmp[1];

        try {
            date = LocalDate.parse(dateAsString);
        } catch (DateTimeParseException e) {

        }
    }

    /**
     * Formats the task details into text file for saving and loading.
     * @return Task of the event.
     */
    @Override
    public String format() {
        return "E " + super.getStatusInNumber() + " " + super.description + " /" + by;
    }

    /**
     * Returns the task details for Duke to print.
     * @return Task details.
     * @throws NullPointerException Prints the string of event details rather than the converted date format.
     */
    @Override
    public String toString() throws NullPointerException {
        String str = "[E]" + super.toString() + " (" + statement + " ";
        try {
            str = str + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } catch (NullPointerException e) {
            str = "[E]" + super.toString() + " (" + by + ")";
        }
        return str;
    }
}