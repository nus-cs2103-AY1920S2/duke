public class Event extends Task {

    protected String dateTime;

    public Event(String taskName, String dateTime) {
        super(taskName.trim());
        this.dateTime = dateTime.trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime + ")";
    }

}
