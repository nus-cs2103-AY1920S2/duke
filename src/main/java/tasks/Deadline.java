package tasks;

import java.time.format.DateTimeFormatter;
import java.util.Set;

import tasks.Tag;

/**
 * Task that need to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected Date dateBy;

    /**
     * Creates a task with a deadline.
     *
     * @param name   task with a deadline to be completed.
     * @param dateBy date the task needs to be completed by.
     */
    public Deadline(Name name, Date dateBy, Set<Tag> tags) {
        super(name, tags);
        this.dateBy = dateBy;
    }

    /**
     * Returns the string of the task with a deadline indicating [D] for deadline followed by the name
     * then do by date in MMM d yyyy.
     *
     * @return the string of the deadline task indicating [D] for deadline followed by the name
     *     then do by date in MMM d yyyy.
     */
    @Override
    public String toString() {
        assert dateBy != null : "Date should be stored for deadlines";
        return "[D]" + super.toString() + " (by: " + dateBy + ")";
    }
}