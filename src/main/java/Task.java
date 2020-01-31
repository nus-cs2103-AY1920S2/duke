abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() { return this.description; }

    abstract public String getType();

    abstract public Task getCopy();

    @Override
    public String toString() {
        return '[' + this.getType() + "][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
