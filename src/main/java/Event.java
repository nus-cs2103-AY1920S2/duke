public class Event extends Task {
    public Event(String description, String time) {
        super(description, time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}