public class Deadline extends Task {

    String by;

    public Deadline(String deadline, String by) {
        super(deadline);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}