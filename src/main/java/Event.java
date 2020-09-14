import java.util.Date;

/**
 * Represents the event task in the task list.
 * Contains datetime of the event, and description of event.
 */
public class Event extends Task {

    public String dateTime;

    /**
     * Constructor for the class.
     * @param taskName description of the event.
     * @param dateTime time and date of the event.
     */
    public Event(String taskName, String dateTime) {
        super(taskName, "E");
        this.dateTime = dateTime;
    }

    public Event(String taskName, String dateTime, int priority) {
        super(taskName, "E", priority);
        this.dateTime = dateTime;
    }

    @Override
    /**
     * String representation.
     * @return String.
     */
    public String toString() {
        String message = "";
        if (this.getDone()) {
            message += "[" + this.getTaskType() + "]" + "[" + "✓" + "] "
                    + this.getTaskName() + " (at: " + this.dateTime + ")";
        } else {
            message += "[" + this.getTaskType() + "]" + "[" + "✗" + "] "
                    + this.getTaskName() + " (at: " + this.dateTime + ")";
        }

        message += " Priority: " + this.getPriorityString();
        return message;
    }

    public String getDateTime() {
        return this.dateTime;
    }
}
