public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        super.type = Type.D;
        this.by = by;
    }

    public String getDate() {
        return by;
    }

    public String toString() {
        return "[" + super.getType() + "]" + super.toString() + "(by: " + by + ")";
    }

    public String saveString() {
        return getType() + " | " + (getStatus() ? "1" : "0") + " | " + getDescription() + " | " + getDate();
    }
}
