package duketasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline object that the user has to complete by
 */

public class Deadline extends Task {
    protected LocalDate by;
    private static final String DEADLINETASKCODE = "D";

    public Deadline(String desc, LocalDate by) {
        super(desc, DEADLINETASKCODE);
        this.by = by;
    }

    public Deadline(String desc, LocalDate by, String isDone) {
        this(desc, by);
        if (isDone.equals("O")) {
            this.done();
        }
    }

    /**
     * Used to return a formatted string of the general status of this task
     * that is used every time the details of the tasks needs to be printed
     *
     * @return String of task details
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", this.taskCode,
                super.toString(), by.format(DateTimeFormatter.ofPattern("MMM d yyy")));
    }

    /**
     * Return a formatted string to be used to save to file
     *
     * @return String of task details in the preferred save format
     */
    @Override
    public String getSaveString() {
        return super.getSaveString() + "|"
                + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
