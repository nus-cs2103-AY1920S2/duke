public class Deadline extends Task {
    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public Deadline(String name, String by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String getMnemonic() {
        return "D";
    }

    @Override
    public String toString() {
        return "[" + getMnemonic() + "]" + super.toString() + " (by: " + by +  ")";
    }
}
