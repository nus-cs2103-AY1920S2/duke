package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents tasks that start at a specific time and ends at a specific time
 * e.g., team project meeting on 2/10/2019 2-4pm
 */
public class Event implements Task {
    protected String description;
    protected LocalDate eventTime;
    protected boolean isDone;

    public Event(String description, String eventTime) {
        this(description, eventTime, false);
    }

    public Event(String description, String eventTime, boolean isDone) throws DateTimeException {
        this.description = description;
        this.eventTime = LocalDate.parse(eventTime);
        this.isDone = isDone;
    }

    public LocalDate getEventTime() {
        return eventTime;
    }

    /**
     * Returns a String (Unicode Character) based on duke.task.Task completion status.
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
        // duke.task.Deadline in yyyy-mm-dd format (e.g. 2020-10-15)
        // Output in MMM d yyyy e.g. (Oct 15 2020)
        // e.g. format: [E][✗] project meeting (at: Oct 15 2020)
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description,
                eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * To return a String representation of duke.task.Event instance
     * @return String representing task in save file
     */
    @Override
    public String stringToSaveToDisk() {
        // e.g. format: event,0,project meeting,2020-03-12
        return String.format("event,%s,%s,%s", isDone ? 1 : 0, description,
                eventTime);
    }

    @Override
    public boolean getTaskCompletionStatus() {
        return isDone;
    }
}
