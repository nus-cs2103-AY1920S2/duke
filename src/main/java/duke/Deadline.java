package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline Class extends the Task Class to store objects with a description with a time and date attached.
 * @author qiujingying
 * @version 1.0
 */
public class Deadline extends Task {

    protected String time;
    protected LocalDateTime ldt;
    private static TaskType type = TaskType.DEADLINE;

    /**
     * Creates a Deadline object with a description and specific start and end time and date.
     * @param description details of the Deadline
     * @param time start and end time and date
     */
    public Deadline(String description, String time) throws DukeException {
        super(description, time);
        this.time = time;
        try {
            TimeParser tp = new TimeParser(time);
            ldt = tp.getTime();
        } catch (Exception e) {
            throw new DukeException(time + " is not a valid date time format! "
                    + "\nTry 'help' to look at the correct format");
        }
    }

    /**
     * Returns the type of the Task.
     * @return TaskType.DEADLINE
     */
    public TaskType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ldt.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}
