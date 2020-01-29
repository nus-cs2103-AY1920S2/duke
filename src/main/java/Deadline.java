import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    //tasks that need to be done before a specific date/time
    // e.g., submit report by 11/10/2019 5pm

    protected String by;
    protected LocalDateTime ldt;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        TimeParser tp = new TimeParser(by);
        ldt = tp.getTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ldt.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}
