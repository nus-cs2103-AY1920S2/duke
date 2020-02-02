public class Deadline extends Task {

    protected String connector;
    protected String datetime;

    public Deadline(String description, String connector, String datetime, boolean isDone) {
        super(description, isDone);
        this.connector = connector;
        this.datetime = datetime;
    }

    public Deadline(String description, String connector, String datetime) {
        this(description, connector, datetime, false);
    }

    public String getFileString() {
        return "D|" + isDone + "|" + description + "|" + connector + "|" + datetime;
    }

    @Override
    public String toString() {
        return ("[D][" + getStatusIcon() + "] " + description + " (" + connector + ": " + datetime + ")");
    }

}
