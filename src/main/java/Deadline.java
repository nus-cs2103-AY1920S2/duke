/**
 * This type of tasks need to be done by a certain deadline.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Class constructor.
     *
     * @param description Task description.
     * @param taskType Type of task: Deadline.
     * @param deadline Deadline of task.
     */
    public Deadline(String description, char taskType, String deadline) {
        super(description, taskType);
        this.deadline = deadline;
    }

    /**
     * Class constructor to be used when loading data from hard disk during start-up.
     *
     * @param description Task description.
     * @param taskType Type of task: Deadline.
     * @param deadline Deadline of task.
     * @param isDone Status of task, whether done or not.
     */
    public Deadline(String description, char taskType, String deadline, boolean isDone) {
        super(description, taskType, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return deadline of task.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Returns all information about the task formatted in a single string.
     *
     * @return Information about task.
     */
    public String obtainTaskInfo() {
        String taskInfo = super.obtainTaskInfo();
        return taskInfo + " " + this.getDeadline();
    }
}
