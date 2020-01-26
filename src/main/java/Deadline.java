import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate localDate;
    String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;

        localDate = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}




