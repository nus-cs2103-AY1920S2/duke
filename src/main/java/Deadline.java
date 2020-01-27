/*
 * Deadline
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 21 Jan 2020
 *
 */

/**
 * The Deadline class extends the Task class and it defines
 * the properties that a deadline has.
 * @author Mario Lorenzo
 */

public class Deadline extends Task {
    private String by_schedule;

    /**
     * <p>Constructs a Deadline instance, given the description,
     * and the schedule when the deadline is due.</p>
     * @param description The description of the deadline.
     * @param by_schedule The date when the deadline is due.
     */

    public Deadline(String description, String by_schedule) {
        super(description);
        this.by_schedule = by_schedule;
    }

    /**
     * Returns the due date of the deadline.
     * @return the due date of the deadline.
     */

    public String getDueDate() {
        return this.by_schedule;
    }

    /**
     * toString method overrides the Object's toString method
     * and it contains the mark, the description, as well as
     * the due date of the deadline.
     * @return The String that represents the deadline's details.
     */

    @Override
    public String toString() {
        return String.format("[D] [%s] %s (by: %s)", getStatusIcon(), this.description, this.by_schedule);
    }
}
