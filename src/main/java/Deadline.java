import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;


public class Deadline extends Task {
    protected String by;
    protected LocalDate dueDate;
    protected LocalTime timing;

    /**
     * Constructor for Deadline Class.
     *
     * @param description Deadline description.
     * @param by          Due date for the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        String[] info = by.split(" ");
        this.dueDate = LocalDate.parse(info[0]);
        this.timing = LocalTime.parse("0000", DateTimeFormatter.ofPattern("HHmm"));
        if (info.length > 1) {
            timing = LocalTime.parse(info[1], DateTimeFormatter.ofPattern("HHmm"));
        }
    }

    @Override
    public void snooze(String by) throws DukeException {
        this.by = by;
        String[] info = by.split(" ");
        try {
            this.dueDate = LocalDate.parse(info[0]);
            this.timing = LocalTime.parse("0000", DateTimeFormatter.ofPattern("HHmm"));
        } catch (DateTimeException e) {
            throw new DukeException("Invalid date format for deadline used! "
                    + "Please re-try using the date format 'yyyy-mm-dd HHMM'");
        }
        if(info.length > 1) {
            this.timing = LocalTime.parse(info[1], DateTimeFormatter.ofPattern("HHmm"));
        }
    }

    @Override
    public String toString() {
        assert (dueDate != null): "There is no date provided for this deadline!";
        assert (timing != null): "This deadline has no timing assigned!";
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " " + timing.format(DateTimeFormatter.ofPattern("HHmm")) + ")";
    }
}
