import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected LocalDate by;
    public String type;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.type = "deadline";
    }

    @Override
    public LocalDate getBy() {

        return by;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        if (super.getStatus() == 0) {
            return "[D][✗]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D][✓]" + super.toString() + " (by: " + by + ")";
        }
    }
}