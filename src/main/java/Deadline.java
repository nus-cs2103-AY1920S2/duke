public class Deadline extends Task {
    protected String by;

    Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    String getBy() {
        return by.trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getBy() + ")";
    }
}
