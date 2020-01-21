public class Event extends Task{
    String duration;

    /**
     * Constructor for Event
     * @param description description of Event
     */
    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration + ")";
    }
}
