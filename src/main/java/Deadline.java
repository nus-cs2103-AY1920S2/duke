public class Deadline extends Task {
    /**
     * Constructor for a new Deadline object.
     * @param taskType the type of Task.
     * @param isDone whether the Task is marked as done.
     * @param taskName the name of the Task.
     * @param taskTime the end time of the Task.
     */
    public Deadline(char taskType, boolean isDone, String taskName, String taskTime) {
        super(taskType, isDone, taskName, taskTime);
    }
}
