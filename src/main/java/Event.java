import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a description String, an isDone boolean, and a date
 */
public class Event extends Task{
    protected LocalDate date;

    /**
     * Creates an Event object, an extension of the Task object with an event date
     * @param description a description of the Event
     * @param date a date of the Event
     */
    public Event(String description, LocalDate date){
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (date: " + dateString + ")";
    }
}
