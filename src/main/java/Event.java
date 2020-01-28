/**
 * The Event object extends the Task object in creating a task of type event.
 */

public class Event extends Task {
    /**
     * Constructor for a new Event object.
     * @param isDone whether the Task is marked as done.
     * @param taskName the name of the Task.
     * @param taskTime the end time of the Task.
     */
    public Event(boolean isDone, String taskName, String taskTime) {
        super('E', isDone, taskName, taskTime);
    }
}
