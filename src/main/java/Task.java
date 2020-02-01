public class Task {
    protected String description;
    protected boolean isDone;

    /**
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /***
     *
     * @return
     */
    public String getStatusIcon() {
        return (isDone
                ? "/"
                : "X"); //return tick or X symbols
    }

    /**
     *
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}