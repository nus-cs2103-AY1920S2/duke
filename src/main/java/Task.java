public class Task {
    protected String taskType;
    protected String description;
    protected boolean isDone;
    protected String date;

    public Task(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
        this.isDone = false;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getTaskType() {
        return (taskType.equals("todo") ? "T" : taskType.equals("deadline") ? "D" : "E");
    }

    public void markAsDone() {
        isDone = true;
    }

    public boolean isTaskDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return description + (!taskType.equals("todo") ? " (" + date.split(" ", 2)[0] + ": " +
                date.split(" ", 2)[1] + ")" : "");
    }
}