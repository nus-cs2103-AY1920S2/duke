public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description Description for Task class.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark the task as done or not done.
     * Done: O
     * Not done: X
     *
     * @return return a string stating if task is not done (X) or done (O).
     */
    public String getStatusIcon() {
        if (!isDone) {
            return "X"; //not done
        } else {
            return "O"; //done
        }

        //return (isDone ? "yep" : "no"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
