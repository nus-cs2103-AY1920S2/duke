package duke.task;

import java.time.LocalDateTime;

import static duke.common.DateTimeFormat.OUTPUT_DATE_TIME_FORMAT;

/**
 * Represents a Deadline task in the application.
 * A Deadline task consist of a description and a time by which the task should be completed.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param by          The time by which the Deadline task should be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the serialized form of this Deadline task in the form of a string.
     *
     * @return A string that is the serialized form of this Deadline task.
     */
    @Override
    public String serialize() {
        return String.format("D,%d,%s,%s", isDone ? 1 : 0, description, by);
    }

    /**
     * Returns the string representation of this Deadline task.
     *
     * @return The string representation of this Deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(OUTPUT_DATE_TIME_FORMAT));
    }
}
