public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDesc() {
        return super.description;
    }

    public String getDate() {
        return by;
    }

    public String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}