import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.StringTokenizer;

/**
 * This class encompasses Events, a specific sub-type of Task objects.
 * They comprise an event name accompanied by its event time.
 */
public class Event extends Task {
    protected String input;
    protected String eventTime;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Creates an Event Task representing the event the user
     * wishes to store. If the event date/time is entered in a
     * recognisable format, then the event date/time is stored
     * as a date and time rather than simply a String.
     *
     * @param name Name of event.
     * @param input Date and time of event.
     */
    public Event(String name, String input) {
        super(name);

        this.input = input;
        StringTokenizer st = new StringTokenizer(input);
        String dateString = st.nextToken();
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            try {
                date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy"));
            } catch (DateTimeParseException x) {
                // do nothing
            }
        }

        if (st.hasMoreTokens()) {
            String timeString = st.nextToken();
            try {
                time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
            } catch (DateTimeParseException e) {
                // do nothing
            }
        }

        if (date != null) {
            eventTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            if (time != null) {
                eventTime = eventTime + " " + time.format(DateTimeFormatter.ofPattern("K.mma"));
            }
        } else {
            eventTime = input;
        }
    }

    /**
     * Formats the Event task for display to the user.
     *
     * @return The output string to be displayed.
     */
    public String getTaskDisplay() {
        assert eventTime != null : "Missing event time";
        return " [E][" + getStatusIcon() + "] " + name + " (at: " + eventTime + ")";
    }

    /**
     * Formats the Event task as it is to be stored/saved
     * on the hard disk.
     *
     * @return The string the task is to be saved as.
     */
    public String getSaveDisplay() {
        assert eventTime != null : "Missing event time";
        return "E|" + (isDone ? "1" : "0") + "|" + name + "|" + eventTime + "\n";
    }

    public String getType() {
        return "E";
    }
    public String getInput() {
        return input;
    }
}