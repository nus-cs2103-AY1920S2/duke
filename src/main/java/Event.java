public class Event extends Task {
    String time_frame;
    public Event(String description, String time_frame) {
        super(description);
        this.time_frame = time_frame;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time_frame + ")";
    }
}
