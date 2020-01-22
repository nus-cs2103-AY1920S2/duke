package duke.pack;

public class Event extends Task {
    protected String at;

    /**
     * creates an event type of task
     * @param description task to be done
     * @param at date and time of event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
