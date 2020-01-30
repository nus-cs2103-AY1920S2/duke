public class DeadLine extends Task {

    protected String by;

    public DeadLine(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    public String toFileString() {
        return "D " + super.toFileString() + " | " + this.by;
    }
}