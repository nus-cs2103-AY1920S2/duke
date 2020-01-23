public class Task {
    /**
     * Task class for all tasks entered by the user
     */
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return Y (done) or N (not done) symbols
    }

    public void taskIsDone() {
        this.isDone = true;
    }
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}