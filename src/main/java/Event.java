public class Event extends Task {
    String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
