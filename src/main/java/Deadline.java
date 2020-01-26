public class Deadline extends Task {

    /** as good practice every class should have it's own private serialVersionUID */
    private static final long serialVersionUID = -5240102332818031942L;
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