package duke.task;

/**
 * Represents an Event task.
 * It has a description, a finish time, and is marked as done or not.
 */
public class Event extends Task {
    protected String time;

    /**
     * Constructs a new Event instance.
     * @param description The description of the event task.
     * @param isDone Whether the event task is done or not.
     * @param time The time by which the event task must be done.
     */
    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Returns the done time of the event task.
     * @return The time by which the event task must be done.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Sets a new time for the event task.
     * @param newTime The new time specified by the user.
     */
    public void setTime(String newTime) {
        this.time = newTime;
    }

    @Override
    public String toString() {
        String str = "[E]";
        if (this.isDone) {
            str += "[O] ";
        } else {
            str += "[X] ";
        }
        str += this.description;
        str += " (at: " + this.time + ")";
        return str;
    }
}