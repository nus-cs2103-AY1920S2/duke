import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    //protected String by = null;
    protected LocalDateTime by = null;

    public Deadline(String description, String by, DateTimeFormatter format) {
        super(description);
        //this.by = by;
        this.by = LocalDateTime.parse(by, format);
    }

    //public Deadline(String description, String by, String retrieved) {
    //    super(description);
        //this.at = at;
    //    this.by = LocalDateTime.parse(by, formatter);
    //}

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(formatter) + ")";
    }

    @Override
    public String fileString() {
        return "D | " + this.getStatusIcon() + " | " + description + " | " +
                by.format(formatter);
    }
}