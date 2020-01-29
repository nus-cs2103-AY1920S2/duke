package duke.tasks;

/**
 * Event class for tasks that have a specific time of happening.
 */
public class Event extends Task {

    /**
     * Provides the time of the event's happening.
     */
    private final String time;

    /**
     * Constructor for the Event Task.
     *
     * @param command full raw text entered to create the Event Task.
     * @param time time during which the Event occurs.
     */
    public Event(String command, String time) {
        super(command);
        this.time = time;
    }

    /**
     * Prints a message indicating the Event Task has been added.
     */
    @Override
    public void taskAddedMessage() {
        printLine();
        print("==> Added unique Event: " + this);
        printLine();
    }

    /**
     * Provides a String representation of the Event Task object with tag [E] and its time of happening.
     *
     * @return String representation of the Event Task with time and tag.
     */
    @Override
    public String toString() {
        return "[E] " + this.command + " (at: " + this.time + ")";
    }

}
