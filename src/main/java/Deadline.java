import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a sub-class of Task.
 * Represents when to finish a certain task by.
 */
public class Deadline extends Task {

    String by;
    String i;
    LocalDate date;

    /**
     * Constructor that takes in 2 params.
     * @param deadline Name of the task.
     * @param by Deadline for the task.
     */
    public Deadline(String deadline, String by) {
        super(deadline);
        this.by = by;
        this.date = LocalDate.parse(by);
    }

    /**
     * Constructor that takes in 3 params.
     * @param deadline Name of the task.
     * @param by Deadline for the task.
     * @param i Defines the done status of the task. Reads either 1 (complete) or 0 (incomplete).
     */
    public Deadline(String deadline, String by, String i) {
        super(deadline);
        this.i = i;
        this.by = by;
        if (i.equals("1")) {
            this.doneStatus = true;
        } else {
            this.doneStatus = false;
        }
        this.date = LocalDate.parse(by);
    }

    /**
     * Returns the format to be saved in the output txt file.
     * @return Returns format in as a string.
     */
    @Override
    public String save() {
        int myInt = doneStatus ? 1 : 0;
        return "D , " + myInt + " , " + taskName + " , " + by;
    }

    @Override
    public String toString() {
        String formattedBy = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}