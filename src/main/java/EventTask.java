import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with an event.
 */
public class EventTask extends Task {
    LocalDate at;
    
    public EventTask(String desc, LocalDate at) {
        super(desc);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override 
    public int compareTo(Task other) {
        return this.at.compareTo(((EventTask) other).at);
    }
}