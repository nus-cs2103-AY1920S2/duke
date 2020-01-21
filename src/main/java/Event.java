public class Event extends Task {
    String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    //need to convert time to () form

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " " + time;
    }
}
