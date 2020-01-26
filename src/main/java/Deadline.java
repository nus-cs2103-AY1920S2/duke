import java.time.*;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String by;
    protected LocalDate dueDate;
    protected LocalTime timing;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        String[] info = by.split(" ");
        this.dueDate = LocalDate.parse(info[0]);
        this.timing = LocalTime.parse("0000", DateTimeFormatter.ofPattern("HHmm"));
        if(info.length > 1) {
            timing = LocalTime.parse(info[1], DateTimeFormatter.ofPattern("HHmm"));
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " " + timing.format(DateTimeFormatter.ofPattern("HHmm")) + ")";
    }
}
