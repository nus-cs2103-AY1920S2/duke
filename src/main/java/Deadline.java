public class Deadline extends Task {

    protected String connector;
    protected String datetime;

    public Deadline(String description, String connector, String datetime) {
        super(description);
        this.description = description;
        this.connector = connector;
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return ("[D][" + getStatusIcon() + "] " + description + " (" + connector + ": " + datetime + ")");
    }

}
