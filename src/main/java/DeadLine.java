import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadLine extends Task {

    protected LocalDateTime dateTime;

    public DeadLine(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dateTime.format(DateTimeFormatter.ofPattern("" +
                "MMM d yyyy, h:mm a")));
    }
}