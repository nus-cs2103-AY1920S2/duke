
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * <h1> Deadline </h1>
 * The DeadLine class extends the Task Class. The DeadLine class is associated with Tasks that have a deadline.
 * Hence, the LocalDateTime attribute of this class is derived from what comes after "/by" in the user's command
 * <p>The Deadline class contains a LocalDateTime object</p>
 */

public class Deadline extends Task {

    protected LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dateTime.format(DateTimeFormatter.ofPattern(""
                + "MMM d yyyy, h:mm a")));
    }

    public String toFileString() {
        return "D " + super.toFileString() + " | " + this.dateTime;
    }

    public String getType(){
        return "deadline";
    }

    public void edit(LocalDateTime dt) {
        this.dateTime = dt;
    }
}