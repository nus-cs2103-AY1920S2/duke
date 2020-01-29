public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescriptionWithIsDone() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public abstract String getFullDescription();
}