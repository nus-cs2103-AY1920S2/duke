package duke.task;

/**
 * The super-class for all Task objects.
 * Contains attributes to store important Task information, and their getters/setters.
 */
public class Task {
    public static final char TICK = 'Y';
    public static final char CROSS = 'N';

    /**
     * The description of the Task
     */
    protected String description;

    /**
     * The completion status of the Task.
     */
    protected boolean isDone;

    /**
     * The priority level of the Task, specified as a Priority enum type.
     * Initialised to LOW priority by default.
     */
    protected Priority priority = Priority.LOW;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task t = (Task) o;

        return t.description.equals(this.description) && (Boolean.compare(t.isDone, this.isDone)==0);
    }

    /**
     * Gets the status icon of the Task, indicating its completion status.
     * @return Status Icon of the Task as a character.
     */
    public char getStatusIcon() {
        return (isDone ? Task.TICK : Task.CROSS); //return tick or X symbols
    }

    /**
     * Marks this Task as done, regardless of it's initial state of completeness.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the description of the Task object.
     * @return Returns the description of this Task object as a string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the priority level of the Task.
     * @return a Priority enum type.
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Sets the priority of this Task to a user defined Priority enum type p.
     * @param p
     */
    public void setPriority(Priority p) {
        this.priority = p;
    }
}
