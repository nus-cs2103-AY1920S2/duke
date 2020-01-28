import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        super.type = Type.D;
        this.by = LocalDateTime.parse(by, inFormatter);
    }

    public String getDate() {
        return by.format(inFormatter);
    }

    public String toString() {
        return "[" + super.getType() + "]" + super.toString() + "(by: " + by.format(outFormatter) + ")";
    }

    public String saveString() {
        return getType() + " | " + (getStatus() ? "1" : "0") + " | " + getDescription() + " | " + getDate();
    }
}
