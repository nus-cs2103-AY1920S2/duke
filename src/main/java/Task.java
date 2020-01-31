public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static int totalTasks = 0;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String getType();
    public abstract void taskSummary();
    public abstract String saveString();

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone? "[✓]" : "[✗]");
    }
}
