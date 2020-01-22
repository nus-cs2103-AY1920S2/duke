public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "X"); //return Y or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "["+ getStatusIcon() + "] " + getDescription();
    }
}
