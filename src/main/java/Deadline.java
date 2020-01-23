public class Deadline extends Task {

    protected String datetime;

    public Deadline(String description) {
        super(description);
        String[] parts = description.split("/");
        this.description = parts[0];
        this.datetime = parts[1].substring(3);
    }

    @Override
    public String toString() {
        return ("[D][" + getStatusIcon() + "] " + description + " (by: " + datetime + ")");
    }

}
