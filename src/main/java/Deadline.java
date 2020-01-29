<<<<<<< HEAD
=======
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

>>>>>>> branch-Level-8
/**
 * Deadline
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class Deadline extends Task {
    private LocalDate due;

    Deadline(String description, LocalDate due) {
        super(description);
        this.due = due;
    }

    protected LocalDate getDueDate() {
        return this.due;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)\n", super.toString(), this.due.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
