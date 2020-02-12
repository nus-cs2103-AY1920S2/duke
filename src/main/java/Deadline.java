import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String by;
    protected LocalDate ld;

    public Deadline(String description, String by) {
        super(description);
        //this.by = by;
        ld = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ld.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
