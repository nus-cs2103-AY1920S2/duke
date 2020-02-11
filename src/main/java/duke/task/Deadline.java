package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Constructs a new deadline task with a description and due date.
     *
     * @param desc    The description of the deadline.
     * @param dueDate The due date of the deadline.
     */
    public Deadline(String desc, LocalDate dueDate) {
        super(desc);
        this.dueDate = dueDate;
    }

    /**
     * Constructs a new deadline task with a description, due date and status.
     *
     * @param desc    The description of the deadline.
     * @param dueDate The due date of the deadline.
     * @param isDone  The status of the deadline.
     */
    public Deadline(String desc, LocalDate dueDate, boolean isDone) {
        super(desc);
        super.setStatus(isDone);
        this.dueDate = dueDate;
    }

    /**
     * Returns a save file entry to be inserted into a save file.
     *
     * @return The save file entry generated from a Deadline
     */
    @Override
    public String generateSaveFileEntry() {
        return String.format("D | %d | %s | %s\n", this.getStatusAsInt(), this.getDescription(),
                this.dueDate.toString());
    }

    /**
     * Returns a string representation of a Deadline.
     *
     * @return The string representation of a Deadline
     */
    @Override
    public String toString() {
        return String.format("[D] [%s] %s (by: %s)", this.getStatus(), this.getDescription(),
                this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}