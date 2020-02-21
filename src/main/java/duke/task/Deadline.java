package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The Deadline class represents a task that need to be
 * done before a specific date/time e.g., submit report by 11/10/2019 5pm
 */
public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Create a deadline task from description, date and time provided.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Get the date of the Deadline task.
     *
     * @return the date of the Deadline task
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Get the time of the Deadline task.
     *
     * @return the time of the Deadline task
     */
    public LocalTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + time.format(DateTimeFormatter.ofPattern("HHmm"))
                + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deadline deadline = (Deadline) o;
        return Objects.equals(date, deadline.date)
                && Objects.equals(time, deadline.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time);
    }
}