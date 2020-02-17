/**
 * FixedDurationTask is a Task that takes a fixed amount of time but does not have a fixed start/end time
 * Described by its <code>String</code> description and
 * a <code>boolean</code> isDone to indicate whether an FixedDurationTask is completed
 * <code>neededHours</code> describes the number of hours needed
 */
public class FixedDurationTask extends Task {
    private int neededHours;

    public FixedDurationTask(String description, int neededHours) {
        super(description);
        this.neededHours = neededHours;
    }

    @Override
    public String toString() {
        return "[F]" + super.toString() + " (needs " + neededHours + " hour(s))";
    }
}
