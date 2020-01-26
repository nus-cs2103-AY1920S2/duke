public class Deadline extends Task {

    protected String by = null;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String fileString() {
        return "D | " + this.getStatusIcon() + " | " + description + " | " + by;
    }
}