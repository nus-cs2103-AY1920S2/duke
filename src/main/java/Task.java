public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getIsDone() {
        if (this.isDone) {
            return "[\u2713] ";
        } else {
            return "[\u2718] ";
        }
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}