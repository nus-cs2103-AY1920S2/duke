package seedu.java.util;

public class Task {
    protected Complete complete;
    protected String task;

    /**
     * The default comnstructor for Task.
     * @param task - piece of task to-do
     */
    public Task(String task) {
        this.complete = new Complete(false);
        this.task = task;
    }

    /**
     * An alternate Task constructor that can modify completion status. Intended for storage class.
     * @param task - piece of task to-do
     * @param bool - Completion status: is it completed?
     */
    public Task(String task, boolean bool) {
        this.complete = new Complete(bool);
        this.task = task;
    }

    /**
     * Converts Complete from true to False.
     * @return the task with complete as true
     */
    public Task completeTask() {
        this.complete = new Complete(true);
        return this;
    }

    public boolean getComplete() {
        return complete.isCompleted();
    }

    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        return ". " + "[ ] " + complete + task;
    }
}