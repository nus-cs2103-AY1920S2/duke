import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    public Deadline(String name, LocalDate by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    public String getBy() {
        return by.toString();
    }

    @Override
    public String getMnemonic() {
        return "D";
    }

    @Override
    public String toString() {
        return "[" + getMnemonic() + "]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyy")) + ")";
    }
}
