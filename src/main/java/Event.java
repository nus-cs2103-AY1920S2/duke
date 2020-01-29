import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate dateAt;
    DateTimeFormatter formattedOutput = DateTimeFormatter.ofPattern("MMM d yyyy");
    DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    Event(String taskName, LocalDate dateAt) {
        super(taskName);
        this.dateAt = dateAt;
    }

    @Override
    public String toString() {
            return "[E]" + super.toString() + "(at:" + dateAt.format(formattedOutput) + ")";
    }
}
