import java.util.Date;

/**
 * Represents the event task in the task list.
 * Contains datetime of the event, and description of event.
 */
public class Event extends Task {

    public String DateTime;

    /**
     * Constructor for the class.
     * @param taskname description of the event.
     * @param DateTime time and date of the event.
     */
    public Event(String taskname, String DateTime) {
        super(taskname, "E");
        this.DateTime = DateTime;
    }

    @Override
    /**
     * String representation.
     * @return String.
     */
    public String toString() {
        String message = "";
        if (this.done) {
            message += "[" + this.Tasktype +"]" + "[" + "\u2713" + "] " + this.taskname + " (at: " + this.DateTime + ")";
        } else {
            message += "[" + this.Tasktype +"]" + "[" + "\u2718" + "] " + this.taskname + " (at: " + this.DateTime + ")";
        }
        return message;
    }
}
