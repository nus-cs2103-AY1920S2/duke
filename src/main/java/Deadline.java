public class Deadline extends Task {

    protected String type;
    protected String by;

    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.type = "D";
        this.by = by;
    }

    @Override
    public String[] toDataTokens() {
        String isDoneString = String.valueOf(super.isDone);
        return new String[] {this.type, isDoneString, super.description, this.by};
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}