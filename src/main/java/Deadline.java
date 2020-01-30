import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate byDate;
    private LocalTime byTime;

    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description.strip());
        this.byDate = byDate;
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)", super.toString(), byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")), byTime);
    }
}