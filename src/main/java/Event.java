public class Event extends Task {
    protected String time;

    public Event(String description, String time){
        super(description);
        this.time = time;
    }

    public String toString() {
        return ("[E] [" + this.getStatusIcon() + "] " + this.description + " (at: " + this.time + ")");
    }
}
