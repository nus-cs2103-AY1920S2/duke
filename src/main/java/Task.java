public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * contains information whether the task is done or not.
     * @return return tick or X symbols
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public int getIsTaskDone() {
        return (isDone ? 1 : 0);
    }

    public String getDescription() {
        return this.description;
    }

    public String markAsDone() {
        this.isDone = true;
        return this.getStatusIcon();
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }
}
