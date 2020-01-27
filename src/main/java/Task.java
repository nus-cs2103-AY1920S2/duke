
public abstract class Task {

    protected String taskName = "";
    protected boolean isDone;
    protected String dateTime = "";
    protected String taskType = "";

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task(String taskName, String dateTime) {
        this.taskName = taskName;
        this.isDone = false;
        this.dateTime = dateTime;
    }

    // To initialise save data for ToDo
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    // To initialise save data for Deadline and Event
    public Task(String taskName, boolean isDone, String dateTime) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.dateTime = dateTime;
    }

    public void completeTask() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    public String getSaveFormat() {
        return this.taskType + "_" + this.isDone + "_" + this.taskName + "_" + this.dateTime;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }

}
