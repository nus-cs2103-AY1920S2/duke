/**
 * Represent tasks that need to be done before a specific date/time
 * e.g., submit report by 11/10/2019 5pm
 */
public class Deadline implements Task {
    protected String description;
    protected String deadline;
    protected boolean isDone;

    public Deadline(String description, String deadline) {
        this(description, deadline, false);
    }

    public Deadline(String description, String deadline, boolean isDone) {
        this.description = description;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    public String getDeadline() {
        return deadline;
    }

    /**
     * Returns a String (Unicode Character) based on Task completion status.
     * @return String representing Unicode character for check mark or cross
     */
    @Override
    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    /**
     * Mark task as done.
     */
    @Override
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark task as incomplete.
     */
    @Override
    public void markAsIncomplete() {
        this.isDone = false;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        // e.g. format: [D][✗] return book (by: June 6th)
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(),
                description, deadline);
    }
}
