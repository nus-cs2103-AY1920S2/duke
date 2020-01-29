public class Deadline extends Task {
    protected String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toStorageString() {
        return String.format("D | %s | %s | %s\n", super.getStatusInteger(), super.getDesc(),
                this.getBy());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
