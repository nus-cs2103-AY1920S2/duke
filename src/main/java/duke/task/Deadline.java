package duke.task;

/**
 * Represents a Deadline task.
 * It has a description, a finish time, and is marked as done or not.
 */
public class Deadline extends Task {
    protected String time;

    /**
     * Constructs a new Deadline instance.
     * @param description The description of the deadline task.
     * @param isDone Whether the deadline task is done or not.
     * @param time The time by which the deadline task must be done.
     */
    public Deadline(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Returns the done time of the deadline task.
     * @return The time by which the deadline task must be done.
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
        String str = "[D]";
        if (this.isDone) {
            str += "[O] ";
        } else {
            str += "[X] ";
        }
        str += this.description;
        str += " (by: " + this.time + ")";
        return str;
    }
}