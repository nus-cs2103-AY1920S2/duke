/**
 * Represents a task to be completed.
 * A <code>Event</code> object contains description of the task e.g., <code>meeting</code> and timing that the task will
 * occur e.g., <code>Sunday 10am</code>.
 */
public class Event extends Task {
    protected String timing;

    public Event(String command, String timing) {
        super(command);
        this.timing = timing;
    }

    /**
     * Returns information about a task, stating that it is E(Event) task, whether it is marked done, the
     * description of the task and the timing at which the event will happen, for the updating of the file saved in
     * hard disk.
     *
     * @return information about the task.
     */
    public String updateFile() {
        return "E - " + getDoneInt() + " - " + getCommand() + " - " + timing;
    }

    /**
     * Updates the timing details for the Event task.
     *
     * @param newTiming timing to be updated.
     */
    public void updateDetails(String newTiming) {
        this.timing = newTiming;
    }

    @Override
    public String toString() {
        return "[E][" + getDoneSymbol() + "] " + getCommand() + "(at: " + timing + ")";
    }
}