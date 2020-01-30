package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task that extends the TaskDate class. A Deadline object is represented by a String description,
 * boolean isDone, LocalDate date and LocalTime time, if a time is specified.
 */
public class Deadline extends TaskDate {

    protected LocalDate date;
    public boolean isTime;
    protected LocalTime time;

    /**
     * Constructs a Deadline object with a description, date and isDone boolean.
     *
     * @param description description of deadline
     * @param date        date of deadline
     * @param isDone      boolean of whether this deadline task is done
     */
    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        this.date = date;
        isTime = false;
    }

    /**
     * Constructs a Deadline object with a description, date, time and isDone boolean.
     *
     * @param description description of deadline
     * @param date        date of deadline
     * @param time        time of deadline
     * @param isDone      boolean of whether this deadline task is done
     */
    public Deadline(String description, LocalDate date, LocalTime time, boolean isDone) {
        super(description, isDone);
        this.date = date;
        this.time = time;
        isTime = true;
    }

    /**
     * Returns the LocalDate of the Deadline task.
     *
     * @return LocalDate of Deadline task
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the LocalTime of the Deadline task.
     *
     * @return LocalTime of Deadline task
     */
    public LocalTime getTime() {
        return this.time;
    }


    @Override
    public String toString() {
        String formattedDate = (this.date).format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (isTime) {
            String formattedTime = (this.time).format(DateTimeFormatter.ofPattern("h:mma"));
            return "[D]" + super.toString() + " (by:" + formattedDate + " " + formattedTime + ")";
        } else {
            return "[D]" + super.toString() + " (by:" + formattedDate + ")";
        }
    }
}

