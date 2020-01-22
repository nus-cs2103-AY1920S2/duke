public class Task {
    protected String description;
    protected boolean isDone;
    protected static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskAdded();
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        isDone = true;
    }

    public static void taskAdded() {
        totalTasks++;
    }

    public static int getTotalTasks() {
        return totalTasks;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
