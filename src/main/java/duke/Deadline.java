/**
 * The Deadline object extends the Task object in creating a task of type deadline.
 */

package duke;

public class Deadline extends Task {
    /**
     * Constructor for a new Deadline object.
     * @param isDone whether the Task is marked as done.
     * @param taskName the name of the Task.
     * @param taskDateTime the end time of the Task.
     */
    public Deadline(boolean isDone, String taskName, String taskDateTime) {
        super('D', isDone, taskName, taskDateTime);
    }

    @Override
    public String toString() {
        return "[D] " + "[" + super.getDoneStatusUnicode() + "] "
                + super.getTaskName() + " (by: " + super.getTaskDateTime() + ")";
    }
}
