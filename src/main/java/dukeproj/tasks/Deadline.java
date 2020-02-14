package dukeproj.tasks;

import dukeproj.Parser;
import dukeproj.enums.TType;
import dukeproj.exception.BadDateException;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    /** Date to finish task by, in LocalDate format. */
    private LocalDate date;

    /**
     * Returns the date of the deadline.
     *
     * @return date in LocalDate format.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the type of task.
     *
     * @return TType.DEADLINE.
     */
    public TType getType() {
        return TType.DEADLINE;
    }

    /**
     * Constructs a deadline task with default false isDone.
     *
     * @param task Description of task.
     * @param date Date to be done by, in String form.
     * @throws BadDateException If date format is wrong.
     */
    public Deadline(String task, String date) throws BadDateException {
        super(task);
        this.date = Parser.dateParser(date);
    }

    /**
     * Constructs a deadline task with user defined isDone.
     *
     * @param isDone Whether the task is done.
     * @param task Description of task.
     * @param date Date to be done by, in String form.
     * @throws BadDateException If date format is wrong.
     */
    public Deadline(boolean isDone, String task, String date) throws BadDateException {
        super(isDone, task);
        this.date = Parser.dateParser(date);
    }

    /**
     * Returns the string form of deadline.
     *
     * @return String form of deadline, will show ✓ if done and ✗ if not.
     */
    @Override
    public String toString() {
        if (isDone) {
            return  "[D][Y] " + task + " (by: "
                    + date.format(Parser.DATE_READ_FORMATTER) + ")";
        } else {
            return "[D][N] " + task + " (by: "
                    + date.format(Parser.DATE_READ_FORMATTER) + ")";
        }
    }
}
