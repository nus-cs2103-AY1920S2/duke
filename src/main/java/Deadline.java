import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class object is created when the user inputs a command that starts with "deadline" and
 * has a valid date input with the "yyyy-MM-dd" format.
 */
public class Deadline extends Task {
    protected LocalDate dateBy;
    DateTimeFormatter formattedOutput = DateTimeFormatter.ofPattern("MMM d yyyy");
    DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    Deadline(String taskName, LocalDate dateBy) {
        super(taskName);
        this.dateBy = dateBy;
    }

    LocalDate getDateBy() {
        return dateBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + dateBy.format(formattedOutput) + ")";
    }
}
