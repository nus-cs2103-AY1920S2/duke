public class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Constructor for the Task class.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task as complete.
     */
    public void doTask() {
        isDone = true;
    }

    /**
     * Snoozes the task.
     *
     * @param by String of new timing
     * @throws DukeException if task cannot be snoozed.
     */
    public void snooze(String by) throws DukeException {

    }

    /**
     * Returns status icon of task.
     *
     * @return Y if task is done, and N if not.
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N");
    }

    /**
     * Returns description of task.
     *
     * @return task description.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
