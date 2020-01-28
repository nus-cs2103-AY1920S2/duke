import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime dL;
    public Deadline(String description, LocalDateTime dL) {
        super(description);
        this.dL = dL;
    }
    public String toString() {
        String dateTimeFormat = dL.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"));
        return ("[D] [" + this.getStatusIcon() + "] " + this.description + " (by: " + dateTimeFormat + ")");
    }
}
