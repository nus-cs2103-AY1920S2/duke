public class Event extends Task {
    String time;

    public Event(String event, String time) {
        super(event);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(at: " + time + ")";
    }
}
