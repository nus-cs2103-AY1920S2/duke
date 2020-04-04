import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected LocalDate by;
    public String type;
    public String tag;


    /**
     * Constructor for Deadline class
     * Takes in a description and by date which will
     * define the task and when the due date is
     *
     * @param description description of the deadline task
     * @param by date of required completion
     */

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.type = "deadline";
    }


    public LocalDate getBy() {

        return by;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        if (super.getStatus() == 0) {
            return "[D][✗]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D][✓]" + super.toString() + " (by: " + by + ")";
        }
    }
}