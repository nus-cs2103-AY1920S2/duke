public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get status icon
     * @return status icon
     */
    public String getStatusIcon() {
        return isDone? "\u2713" : "\u2718";
    }

    /**
     * Set status to done
     */
    public void setStatusDone() {
        this.isDone = true;
    }

    /**
     * Set status to not done
     */
    public void setStatusNotDone() {
        this.isDone = false;
    }

    /**
     * Get the description of the task
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }
}
