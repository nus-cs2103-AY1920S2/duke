public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String period;

    /**
     * Constructor of Task
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.period = "NIL";
    }

    /**
     * Get the period for the task
     * @return period of the task
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Get status icon
     * @return status icon
     */
    public String getStatusIcon() {
        return isDone? "\u2713" : "\u2718";
    }

    /**
     * Get status of the task
     * @return task status
     */
    public boolean getStatus() {
        return isDone;
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

    /**
     * Get the task type
     * @return type of the task
     */
    public String getType() {
        return type;
    }

    /**
     * Return status icon and description of the task
     * @return status icon and description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
