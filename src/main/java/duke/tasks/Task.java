package duke.tasks;

public class Task {
    private String taskName;
    private boolean taskDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.taskDone = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getTaskDone() {
        return this.taskDone;
    }

    public void setTaskDone() {
        this.taskDone = true;
    }

    /**
     * Return a string of the details of the current Task object.
     * Contains the task done status and the task name.
     *
     * @return A String representation of the task details.
     */
    public String toString() {
        return "[" + (getTaskDone()
                ? Character.toString(0x2713)
                : Character.toString(0x2717))
            + "] " + getTaskName();
    }

    public String toFileString() {
        return " | " + getTaskDone()
            + " | " + getTaskName();
    }
}