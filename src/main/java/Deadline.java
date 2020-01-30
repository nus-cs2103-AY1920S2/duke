import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Specific form of a deadline task that is input by the user to Duke.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
        this.date = LocalDate.parse(by);
    }

    public String toLine() {
        int num;
        if (super.done) {
            num = 1;
        } else {
            num = 0;
        }
        return "D/" + num + "/" + super.task + "/" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
