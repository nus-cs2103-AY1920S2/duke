import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Specific form of an event task that is input by the user to Duke.
 */
public class Event extends Task {
    protected String at;
    protected LocalDate date;

    /**
     * Creates an event with the specific task and date to complete the task by.
     * @param task Task to complete within deadline.
     * @param at Date to complete the task.
     */
    public Event(String task, String at) {
        super(task);
        this.at = at;
        this.date = LocalDate.parse(at);
    }

    @Override
    public String toLine() {
        int num;
        if (super.done) {
            num = 1;
        } else {
            num = 0;
        }
        return "E/" + num + "/" + this.task + "/" + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}