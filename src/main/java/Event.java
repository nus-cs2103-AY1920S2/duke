public class Event extends Task {
    private String time;

    public Event(String event) {
        super(event.split("/")[0]);
        this.time = event.split("/")[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + time + ")";
    }
}
