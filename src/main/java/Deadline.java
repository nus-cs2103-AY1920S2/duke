public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String[] date = by.split(" ", 2);
        return "[D]" + super.toString() + " (" + date[0] + ": " + date[1] + ")";
    }
}