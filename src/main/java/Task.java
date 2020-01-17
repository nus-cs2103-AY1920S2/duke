abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatus() {
        return isDone ? "\u2713" : "\u2718";
    }
    
    @Override
    abstract public String toString();
}