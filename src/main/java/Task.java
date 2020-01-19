public class Task {
    protected String description;
    protected boolean isDone;
    protected static int numOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numOfTasks++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static int getNumOfTasks() {
        return numOfTasks;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
