package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A more detailed implementation of the task.Task class with specified deadline.
 */
public class Deadline extends Task {

    private LocalDate date;

    /**
     * Constructs a new instance of task.Deadline.
     *
     * @param description the description of the task.
     * @param date        the deadline of the task.
     */
    public Deadline(String description, LocalDate date) {

        super(description);
        this.date = date;
    }

    /**
     * Gets the date associated with the deadline.
     *
     * @return the date
     */
    public LocalDate getDate() {

        return date;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof Deadline)) {
            return false;
        }

        Deadline deadline = (Deadline) object;
        return compareDeadline(deadline);

    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Compares this deadline with another
     *
     * @param deadline the deadline to compare with
     * @return boolean whether this deadline is the same as the parameter
     */
    private boolean compareDeadline(Deadline deadline) {

        boolean hasSameDescription = (description.equals(deadline.description));
        boolean hasSameDate = (date.equals(deadline.date));

        return hasSameDate && hasSameDescription;
    }

}
