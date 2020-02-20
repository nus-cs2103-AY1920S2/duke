import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String by;
    protected LocalDate ld;

    public Deadline(String description, String by) {
        super(description);
        ld = LocalDate.parse(by);
    }

    public String toSaveForm() {
        return "D , " + super.getStatusIcon() + " , " + description + " , " + ld.toString() + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ld.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
