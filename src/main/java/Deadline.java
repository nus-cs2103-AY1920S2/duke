public class Deadline extends Task {
    /** The cut-off date for this deadline. */
    protected final String by;

    /**
     * Constructs a new deadline with a cut-off date.
     *
     * @param description the details of the deadline
     * @param by the cut-off date as text.
     */
    public Deadline(String description, String by) {
        this(description, false, by);
    }

    private Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public Deadline markDone() {
        return new Deadline(description, true, by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}