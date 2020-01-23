public class Event extends Task {

    protected String datetime;

    public Event(String description) {
        super(description);
        String[] parts = description.split("/");
        this.description = parts[0];
        this.datetime = parts[1].substring(3);
    }

    @Override
    public String toString() {
        return ("[E][" + getStatusIcon() + "] " + description + " (at: " + datetime + ")");
    }

}
