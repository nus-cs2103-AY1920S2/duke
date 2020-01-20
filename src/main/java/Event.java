/**
 * Represents tasks that start at a specific time and ends at a specific time
 * e.g., team project meeting on 2/10/2019 2-4pm
 */
public class Event implements Task {
    protected String description;
    protected String eventTime;
    protected boolean isDone;

    public Event(String description, String eventTime) {
        this(description, eventTime, false);
    }

    public Event(String description, String eventTime, boolean isDone) {
        this.description = description;
        this.eventTime = eventTime;
        this.isDone = isDone;
    }

    public String getEventTime() {
        return eventTime;
    }

    /**
     * Returns a String (Unicode Character) based on Task completion status.
     * @return String representing Unicode character for check mark or cross
     */
    @Override
    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    /**
     * Mark task as done.
     */
    @Override
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark task as incomplete.
     */
    @Override
    public void markAsIncomplete() {
        this.isDone = false;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        // e.g. format: [E][✗] project meeting (at: Aug 6th 2-4pm)
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description,
                eventTime);
    }
}
