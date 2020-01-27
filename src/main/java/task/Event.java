package task;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
/**
 * Represents an event task. A <code>Event</code> object corresponds to a event task e.g.,
 * <code>"project meeting", LocalDate.parse("2020-12-12")</code>
 */
public class Event extends Task {
    LocalDate date;
    public Event(String description, LocalDate date){
        super (description);
        this.date = date;
    }
    /**
     * Returns the text to be saved into the storage file.
     * @return The text to be saved to storage file.
     */
    public String toStringTaskstxt(){
        return "E/" + getStatusIconInBin() + "/" + description + "/" + date.toString() + "\n";
    }
    /**
     * Returns the text representation of the event task to be displayed to the user.
     * @return The text representation of the event task.
     */
    @Override
    public String toString(){
        String fullDate = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " +
                Integer.toString(date.getDayOfMonth()) + " " + Integer.toString(date.getYear());
        return "[E]" + super.toString() + "(at: " + fullDate + ")";
    }
}
