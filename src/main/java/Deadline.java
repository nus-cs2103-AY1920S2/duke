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

    /**
     * The constructor for Deadline tasks.
     * @param taskName The String input of the user.
     * @param dateBy The date input of the user in 'yyyy-MM-dd' format.
     */
    Deadline(String taskName, LocalDate dateBy) {
        super(taskName);
        this.dateBy = dateBy;
    }

    /**
     * Returns the LocalDate of the created Deadline task.
     * @return The LocalDate of the Deadline task created.
     */
    LocalDate getDateBy() {
        return dateBy;
    }

    /**
     * The toString method of the created Deadline task returns "D" to indicate it is a deadline task,
     * the string input of the task and the date of the deadline task.
     * @return The String output of a deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + dateBy.format(formattedOutput) + ")";
    }
}
