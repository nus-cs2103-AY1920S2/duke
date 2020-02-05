public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
//        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone ? "✓" : "✘");
//        return (isDone ? "Y" : "N");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toStringInFile() {
        return toString();
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }
}