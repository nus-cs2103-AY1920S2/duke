import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String deadline) {
        super(deadline.split("/by")[0]);
        this.date = LocalDate.parse(deadline.split("/by")[1].strip());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
