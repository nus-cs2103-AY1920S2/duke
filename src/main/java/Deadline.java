import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        // string by must be of format yyyy-mm-dd
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toDatabaseString() {
        return "D" + "|" + (this.isDone ? "1" : "0") + "|" + this.description +
                "|" + this.by + "\n";
    }
}
