package duke.tasks;

public class Task {
    protected String taskName;
    protected Boolean status;

    /**
     * Constructs new task object with name taskName.
     * @param taskName name of new task.
     */
    public Task(String taskName) {
        assert !taskName.isEmpty();
        this.taskName = taskName;
        this.status = false;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Gets status of a task as a string.
     * @return string indicating if the task is done or not.
     */
    public String getStatus() {
        if (status) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public void setDone() {
        this.status = true;
    }

    @Override
    public String toString() {
        if (status) {
            return "[X] " + getTaskName();
        } else {
            return "[ ] " + getTaskName();
        }
    }
}
