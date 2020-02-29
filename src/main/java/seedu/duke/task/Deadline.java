package seedu.duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Deadline Object.
 */
public class Deadline extends Task {
    protected Date deadlineDate;

    /**
     * Represents a Deadline Object.
     *
     * @param description The details of the deadline.
     * @param deadlineDate The date for the deadline.
     */
    public Deadline(String description, Date deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d yyyy");
        String dateString = simpleDateFormat.format(deadlineDate);
        String formattedDeadlineTime = " (by: "
                + dateString + ")";
        return "[D]" + super.toString() + formattedDeadlineTime;
    }

    public Date getDate() {
        return deadlineDate;
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof Deadline) {
            int cmp = getDate().compareTo(((Deadline) task).getDate());
            if (cmp == 0) {
                return toString().compareTo(task.toString());
            } else {
                return cmp;
            }
        } else if (task instanceof Event) {
            int cmp = getDate().compareTo(((Event) task).getDate());
            if (cmp == 0) {
                return toString().compareTo(task.toString());
            } else {
                return cmp;
            }
        } else {
            return toString().compareTo(task.toString());
        }
    }
}
