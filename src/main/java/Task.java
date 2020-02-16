public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getStatus() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    //Set Task as Done
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.getStatus() + " " + this.description;
    }
}