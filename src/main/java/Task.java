public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getTask() {
        return this.description;
    }

    public String checkDone() {
        return (this.isDone ? "1" : "0");
    }

    public void markAsDone() {
        this.isDone = true;
    }

}
