public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Mark task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark task as incomplete.
     */
    public void markAsIncomplete() {
        this.isDone = false;
    }

    /**
     * Returns a String (Unicode Character) based on Task completion status.
     * @return String representing Unicode character for check mark or cross
     */
    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        // e.g. format: [✓] read book
        return "[" + getStatusIcon() + "] " + description;
    }
}
