package duketasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event object that the user has to complete by
 */

public class Event extends Task {
    protected LocalDate by;
    private static final String EVENTTASKCODE = "E";


    public Event(String desc, LocalDate by) {
        super(desc,
                EVENTTASKCODE);
        this.by = by;
    }

    public Event(String desc, LocalDate by, String isDone) {
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
        return String.format("[%s]%s (at: %s)", this.taskCode,
                super.toString(), this.by.format(DateTimeFormatter.ofPattern("MMM d yyy")));
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
