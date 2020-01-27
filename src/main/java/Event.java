public class Event extends Task {

    protected String location;

    public Event(String taskTitle, String location) {
        super(taskTitle);
        this.location = location;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + location + ")";
    }
}
