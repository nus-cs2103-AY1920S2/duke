/**
 * The Deadline object extends the Task object in creating a task of type deadline.
 */

public class Deadline extends Task {
    /**
     * Constructor for a new Deadline object.
     * @param isDone whether the Task is marked as done.
     * @param taskName the name of the Task.
     * @param taskTime the end time of the Task.
     */
    public Deadline(boolean isDone, String taskName, String taskTime) {
        super('D', isDone, taskName, taskTime);
    }
}
