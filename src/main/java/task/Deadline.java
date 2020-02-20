package task;

/**
 * Deadline subclass which extends from task.Task (parent class)
 */
public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * @return the deadline description and status icon
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.getDescription() + "(" + by + ")";
    }

    @Override
    public String getDate(){
        return this.by;
    }
}
