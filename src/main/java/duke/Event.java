/**
 * The Event object extends the Task object in creating a task of type event.
 */

package duke;

public class Event extends Task {
    /**
     * Constructor for a new Event object.
     * @param isDone whether the Task is marked as done.
     * @param taskName the name of the Task.
     * @param taskDateTime the end time of the Task.
     */
    public Event(boolean isDone, String taskName, String taskDateTime) {
        super('E', isDone, taskName, taskDateTime);
    }

    @Override
    public String toString() {
        return "[E] " + "[" + super.getDoneStatusUnicode() + "] "
                + super.getTaskName() + " (at: " + this.getTaskDateTime() + ")";
    }
}
