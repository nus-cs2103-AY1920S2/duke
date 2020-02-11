package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Encapsulates an event task which has both a description and an event date.
 */
public class Event extends DatedTask {
    private LocalDate eventTime;
    
    /**
     * Constructs a new Event instance.
     * @param description Task description
     * @param eventTime Event time for the task
     */
    public Event(String description, LocalDate eventTime) {
        super(description);
        
        assert description != null && eventTime != null; //Precondition: non-null arguments
        
        this.eventTime = eventTime;
    }

    /**
     * Returns the event date.
     * @return Event date
     */
    public LocalDate getDate() {
        return eventTime;
    }
    
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description,
                eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}

