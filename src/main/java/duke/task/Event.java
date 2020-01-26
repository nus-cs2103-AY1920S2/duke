package duke.task;

public class Event extends Task {

    /**
     * Constructor for duke.task.Event
     * @param description description of duke.task.Event
     */
    public Event(String description, String duration) {
        super(description);
        this.period = duration;
        type = "event";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + period + ")";
    }
}
