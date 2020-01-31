import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class inherits from Task and is created when user input starts with "event".
 */
public class Event extends Task {
    protected LocalDate dateAt;
    DateTimeFormatter formattedOutput = DateTimeFormatter.ofPattern("MMM d yyyy");
    DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructor for Event tasks that takes in a task name and a date in 'yyyy-MM-dd' format.
     * @param taskName String task name.
     * @param dateAt LocalDate input in 'yyyy-MM-dd' format.
     */
    Event(String taskName, LocalDate dateAt) {
        super(taskName);
        this.dateAt = dateAt;
    }

    /**
     * Returns the LocalDate of the created Event object.
     * @return The LocalDate of the Event task.
     */
    LocalDate getDateAt() {
        return dateAt;
    }

    /**
     * The toString method of the Event task contains "[E]" which indicates its an Event task and
     * the task name as well as the date of the event.
     * @return A String containing the details of the event object.
     */
    @Override
    public String toString() {
            return "[E]" + super.toString() + "(at:" + dateAt.format(formattedOutput) + ")";
    }
}
