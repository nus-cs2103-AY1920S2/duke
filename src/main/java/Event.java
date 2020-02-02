public class Event extends Task {

    protected String connector;
    protected String datetime;

    public Event(String description, String connector, String datetime, boolean isDone) {
        super(description, isDone);
        this.connector = connector;
        this.datetime = datetime;
    }

    public Event(String description, String connector, String datetime) {
        this(description, connector, datetime, false);
    }

    public String getFileString() {
        return "E|" + isDone + "|" + description + "|" + connector + "|" + datetime;
    }


    @Override
    public String toString() {
        return ("[E][" + getStatusIcon() + "] " + description + " (" + connector + ": " + datetime + ")");
    }

}
