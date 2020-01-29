import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    LocalDate at;
    public EventTask(String desc, LocalDate at) {
        super(desc);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}