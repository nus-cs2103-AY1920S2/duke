public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getStatusInteger() {
        return isDone ? "1" : "0";
    }

    public String getDesc() {
        return this.desc;
    }

    public String toStorageString() {
        // empty, specified by each subclasses
        return "";
    }

    @Override
    public String toString() {
        String doneStatus = "[" + getStatusIcon() + "]";
        return doneStatus + " " + desc;
    }
}
