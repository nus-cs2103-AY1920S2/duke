import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate localDate;

    public Deadline(String description, LocalDate localDate) {
        super(description);
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}




