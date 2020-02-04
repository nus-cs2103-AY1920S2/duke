import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String content, String by) {
        super(content);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toStore() {
        return "[D]" + super.toStore() + " (at: "
                + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyy")) + ")";
    }
}
