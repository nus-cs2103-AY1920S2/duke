/**
 * The Event class represents a task that start at a specific time
 * and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm
 */
public class Event extends Task {
    protected String atDateAndTime;

    public Event(String description, String atDateAndTime) {
        super(description);
        this.atDateAndTime = atDateAndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atDateAndTime + ")";
    }
}
