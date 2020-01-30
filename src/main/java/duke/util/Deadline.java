package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class Deadline extends Task {
    private LocalDate due;

    /**
     * Constructs an Event instance.
     * @param description The description or details of the Event.
     * @param due The due date of the Deadline.
     */
    public Deadline(String description, LocalDate due) {
        super(description);
        this.due = due;
    }

    /**
     * Gets due date of the Deadline.
     * @return Due date of the Deadline.
     */
    public LocalDate getDueDate() {
        return this.due;
    }

    /**
     * Overrides the Object's toString method
     * and contains the task identifier, status icon, description,
     * and due date of the deadline.
     * @return The String that containing the Deadline's details.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.due.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
