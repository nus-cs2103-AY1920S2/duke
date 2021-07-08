package duke.tasks;

public abstract class Task {
    protected TaskType TYPE;
    protected String description;
    protected boolean isDone;

    /**
     * A default constructor with no parameters, for stub.
     */
    public Task() {

    }

    public Task(TaskType TYPE, String status, String description) {
        this.TYPE = TYPE;
        this.isDone = status.equals("1");
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "[✔]" : "[✘]"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public int getDoneInt() {
        return (isDone ? 1 : 0);
    }

    public TaskType getTaskType() {
        return this.TYPE;
    }

    public void markAsDone() {
        isDone = true;
    }

    public boolean changeDescription(String update) {
        this.description = update;
        return true;
    }

    public abstract boolean changeDate(String update);

    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

}
