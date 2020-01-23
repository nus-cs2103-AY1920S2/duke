public class Deadline extends Task {

    protected String by;
    public String type;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "deadline";
    }

    @Override
    public String getBy() {

        return by;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        if (super.getStatus() == 0) {
            return "[D][✗]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D][✓]" + super.toString() + " (by: " + by + ")";
        }
    }
}