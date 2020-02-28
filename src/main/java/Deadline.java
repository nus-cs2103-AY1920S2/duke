import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + by
                + ")";
    }

    public String convert() {
        return "D"
                + super.convert()
                + " | "
                + by;
    }
}