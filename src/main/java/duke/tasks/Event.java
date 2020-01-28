package duke.tasks;

import duke.Parser;
import duke.enums.TType;
import duke.exception.BadDateException;

import java.time.LocalDate;

/**
 * Represents an event, which is a task to attend to on a certain date.
 */
public class Event extends Task {
    /** Date of event, in LocalDate format. */
    private LocalDate date;

    /**
     * @return date in LocalDate format.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return type of task in TType format.
     */
    public TType getType() {
        return TType.EVENT;
    }

    /**
     * Constructs an event with default false isDone.
     * @param task Description of event.
     * @param date Date of event, in String form.
     * @throws BadDateException If date format is wrong.
     */
    public Event(String task, String date) throws BadDateException {
        super(task);
        this.date = Parser.dateParser(date);
    }

    /**
     * Constructs an event with user defined isDone.
     * @param done Whether the event is done.
     * @param task Description of event.
     * @param date Date of event, in String form.
     * @throws BadDateException If date format is wrong.
     */
    public Event(boolean done, String task, String date) throws BadDateException {
        super(done, task);
        this.date = Parser.dateParser(date);
    }

    /**
     * @return String form of task, will show ✓ if done and ✗ if not.
     */
    @Override
    public String toString() {
        if (done) return  "[E][✓] " + task + " (at: " +
                date.format(Parser.DATE_FORMATTER) + ")";
        else return "[E][✗] " + task + " (at: " +
                date.format(Parser.DATE_FORMATTER) + ")";
    }
}
