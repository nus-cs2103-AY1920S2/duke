import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;
    private String formattedDate;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getFullDescription() {
        return "[D]" + super.getDescriptionWithIsDone() + " (by: " + this.formattedDate + ")";
    }
}