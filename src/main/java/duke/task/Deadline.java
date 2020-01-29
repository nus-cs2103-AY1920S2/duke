package duke.task;
import java.time.format.TextStyle;
import java.time.LocalDate;

import java.util.Locale;
/**
 * Represents a deadline task. A <code>Deadline</code> object corresponds to a deadline task e.g.,
 * <code>"project meeting", LocalDate.parse("2020-12-12")</code>
 */
public class Deadline extends Task {
    LocalDate date;
    public Deadline(String description, LocalDate date){
        super (description);
        this.date = date;
    }
    /**
     * Returns the text to be saved into the storage file.
     * @return The text to be saved to storage file.
     */
    public String toStringTasks(){
        return "D/" + getStatusIconInBin() + "/" + description + "/" + date.toString() + "\n";
    }
    /**
     * Returns the text representation of the deadline task to be displayed to the user.
     * @return The text representation of the deadline task.
     */
    @Override
    public String toString(){
        String fullDate = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " +
                Integer.toString(date.getDayOfMonth()) + " " + Integer.toString(date.getYear());
        return "[D]" + super.toString() + "(by: " + fullDate + ")";
    }
}
