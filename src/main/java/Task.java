public class Task {

    protected String description;
    protected boolean isDone;
    protected static int totalTaskCount = 0;

    public Task(String description) {
        this.description = description;
        isDone = false;
        totalTaskCount++;
    }

    public static int getTotalTaskCount() {
        return totalTaskCount;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘");
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
