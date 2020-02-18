/**
 * This class is the base class for the {@code Todo}, {@code Deadline}, and
 * {@code Event} classes.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for the {@code Task} class.
     * 
     * @param description description of the task
     * @param isDone      indicates if this task is done
     */
    public Task(String description, boolean isDone) {
        assert description != null;
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescriptionWithIsDone() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public abstract String getFullDescription();

    public abstract boolean isSimilarTask(Task task);
}