import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, inFormatter);
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(outFormatter) + ")";
    }
}
