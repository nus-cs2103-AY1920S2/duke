public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description Description for task.
     * @throws DukeException If description is not specified.
     */
    public Task(String description) throws DukeException {
        if (description.strip().equalsIgnoreCase("")) {
            throw new DukeException("Invalid description");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns tick icon or cross icon.
     *
     * @return tick icon || cross icon.
     */
    public String getStatusIcon() {
        return (isDone
                ? "\u2713" //return ✓ symbol.
                : "\u2718"); //return X symbol.
    }

    /**
     * Sets done status of a task.
     *
     * @param doneStatus Done status of a task.
     */
    public void isDone(String doneStatus) {
        if (doneStatus.equalsIgnoreCase("\u2713]")) { //check if doneStatus = ✓ symbol.
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    /**
     * Set task to be done.
     */
    public void doneTask() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Same as toString.
     *
     * @return Task toString.
     */
    public String saveToList() {
        return "[" + getStatusIcon() + "] " + description;
    }
}