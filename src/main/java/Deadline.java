import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Deadline extends Task {
    String due;
    String parsedDue;

    /**
     * Creates deadline type.
     *
     * @param description Describes the task.
     */
    public Deadline(String[] description) {
        super(description[0]);
        due = (description[1].split(" ", 2))[1];
        modifyDateFormat();
    }

    /**
     * Changes format of date.
     */
    public void modifyDateFormat() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime parsedDate = LocalDateTime.parse(this.due, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        this.parsedDue = parsedDate.format(outputFormatter);
    }

    /**
     * Creates a deadline.
     *
     * @param description Describes the task.
     * @param dueDate DueDate of task.
     * @param isDone Informs whether task is done.
     */
    public Deadline(String description, String dueDate, boolean isDone) {
        super(description, isDone);
        due = dueDate;
    }

    public String getDescription() {
        return super.description;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " + this.parsedDue + ")";
    }

    public String addToFile() {
        return "D | " + super.addToFile() + " | " + this.parsedDue;
    }
}
