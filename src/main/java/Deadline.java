public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description.strip());
        this.by = by.strip();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}