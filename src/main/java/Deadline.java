import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Encapsulates a deadline task which has both a description and a deadline date.
 */
public class Deadline extends Task {
    private LocalDate deadline;
    
    /**
     * Constructs a new Deadline instance.
     * @param description Task description
     * @param deadline Deadline for the task
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }
    
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description,
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}


