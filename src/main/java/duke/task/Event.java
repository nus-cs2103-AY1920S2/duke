package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/** Entity class representing a task of type Event */
public class Event extends Task {
    /** as good practice every class should have it's own private serialVersionUID. */
    private static final long serialVersionUID = 7761205144753475365L;
    
    private LocalDate atDate;
    private LocalTime atTime;

    public Event(String description, LocalDate atDate, LocalTime atTime) {
        super(description.strip());
        this.atDate = atDate;
        this.atTime = atTime;
    }
    
    /** toString implementation */
    @Override
    public String toString() {
        return String.format(
            "[E]%s (at: %s %s)",
            super.toString(), atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")), atTime);
    }
}