import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This is a subclass of task which simulates tasks with event time.
 */
public class Event extends Task{

    /** Event time of this task */
    protected LocalDate at;

    /**
     * Class constructor inherits form Task constructor and add event time.
     *
     * @param description Description of this Event.
     * @param at Event time.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Override String representation of event.
     *
     * @return Type E and its description and event time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
