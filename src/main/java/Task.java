/**
 * Each object of class Task represents a task to be saved.
 */
public class Task {

    private String description;
    private String taskType;
    private boolean isDone;
    private boolean prevStatus;

    /**
     * Class constructor.
     *
     * @param description Task description.
     * @param taskType Type of task: deadline, event, to-do.
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;
        this.prevStatus = false;
    }

    /**
     * Class constructor to be used when loading data from hard disk during start-up.
     *
     * @param description Task description.
     * @param taskType Type of task: deadline, event, to-do.
     * @param isDone Status of task, whether completed or not.
     */
    public Task(String description, String taskType, boolean isDone) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = isDone;
    }

    /**
     * Returns symbol representing status of task.
     *
     * @return If task is done, return "Y", else return "X".
     */
    public String getStatusIcon() {
        if (isDone) {
            return ("Y");
        } else {
            return ("X");
        }
    }

    /**
     * Returns description of task.
     *
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task.
     *
     * @return Type of task.
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Mark task as done.
     */
    public void setDone() {
        prevStatus = isDone;
        isDone = true;
    }

    /**
     * Reset the previous status of the task.
     */
    public void resetPrevStatus() {
        boolean tempStatus = isDone;

        isDone = prevStatus;
        prevStatus = tempStatus;
    }

    /**
     * Returns all information about the task formatted in a single string.
     *
     * @return Information about task.
     */
    public String obtainTaskInfo() {
        return "[" + this.getTaskType() + "]["
                + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Returns all information about task formatted for storage in file.
     *
     * @return All information about task.
     */
    public String formatForFile() {
        return this.getTaskType() + "|" + this.getStatusIcon() + "|" + this.getDescription();
    }
}
