public class Event extends Task {

    protected String connector;
    protected String datetime;

    public Event(String description, String connector, String datetime) {
        super(description);
        this.description = description;
        this.connector = connector;
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return ("[E][" + getStatusIcon() + "] " + description + " (" + connector + ": " + datetime + ")");
    }

}
