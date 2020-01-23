public class Task {
    protected String currentTask;
    protected boolean isDone;

    public Task(String currentTask) {
        this.currentTask = currentTask;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    //Overload toString method
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.currentTask;
    }
}
