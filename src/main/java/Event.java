import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;
    private String formattedDate;

    public Event(String description, String at, LocalDate date) {
        super(description);
        this.date = date;
        this.formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String getFullDescription() {
        return "[E]" + super.getDescriptionWithIsDone() + " (at: " + this.formattedDate + ")";
    }
}