import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h1> Event </h1>
 * The Event class extends the Task Class. The Event class is associated with Tasks that occurs at a
 * designated time and date.
 * Hence, the LocalDateTime attribute of this class is derived from what comes after "at" in the user's command
 * <p>The Event class contains a LocalDateTime object</p>
 */
public class Event extends Task {

    protected LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {

        return String.format("[E]%s (at: %s)", super.toString(),
                this.dateTime.format(DateTimeFormatter.ofPattern("" + "MMM d yyyy, h:mm a")));
    }

    public String toFileString() {
        return "E " + super.toFileString() + " | " + this.dateTime;
    }
}