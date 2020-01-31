package duketasks;

public class Task {
    protected boolean isDone;
    protected String isDoneString;
    protected String taskName;
    protected String taskCode;

    public Task(String taskName, String taskCode) {
        this.taskName = taskName;
        this.isDoneString = "X";
        this.isDone = false;
        this.taskCode = taskCode;
    }

    public void done() {
        isDone = true;
        isDoneString = "O";
    }

    public String getIsDoneString() {
        return isDoneString;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getSaveString() {
        String currEntry = String.format("%s|%s|%s", this.taskCode, this.getIsDoneString(), this.getTaskName());
        return currEntry;
    }

    public String toString() {
        String output = String.format("[%s] %s", isDoneString, taskName);

        return output;
    }
}