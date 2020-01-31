import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents an <code>Event</code>, which is a subclass of a <code>Task</code>. An <code>Event</code> includes
 *  * an atDate which represents the date of the respective <code>Event</code> occurring.
 */
public class Event extends Task {
    LocalDate atDate;
    String time24Hr;

    /**
     * Constructor for Event. Default isDone boolean value is false.
     *
     * @param description Description of event
     * @param date Date of the event
     * @param time Time of the event
     */
    public Event(String description, String date, String time) {
        super(description);
        this.atDate = LocalDate.parse(date);
        this.time24Hr = time;
    }

    /**
     * Another constructor for Deadline which allows explicit assigning of isDone.
     *
     * @param description Description of event
     * @param date Date of the event
     * @param time Time of the event
     * @param isDone True if event has been completed, false if not.
     */
    public Event(String description, String date, String time, boolean isDone) {
        super(description, isDone);
        this.atDate = LocalDate.parse(date);
        this.time24Hr = time;
    }

    /**
     * Returns a string representing time in 12-hour format eg. 11.59pm
     *
     * @param timeString String representing time in 24-hour format eg. 2359
     * @return Time in 12-hour format
     */
    public String formatTime12Hour(String timeString) {
        int time = Integer.parseInt(timeString);
        boolean isAM = time < 1200;
        if (!isAM && time >= 1300) {
            time = time - 1200;
        }
        int mins = time % 100;
        int hour = time / 100;
        return hour + "." + String.format("%02d", mins) + (isAM ? "am" : "pm");
    }

    @Override
    public String toString() {
        String checkbox = "[" + super.getStatusIcon() + "]";
        String dateFormat = atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + checkbox + " " + super.toString()
                + " (at: " + dateFormat + ", " + formatTime12Hour(time24Hr) + ")";
    }
}