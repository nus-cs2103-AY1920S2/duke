package duke;

/**
 * The type Deadline.
 */
public class Deadline extends Task {

    /**
     * The Date and time.
     */
    DateAndTime dateAndTime;
    /**
     * The Date.
     */
    protected String date;

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description
     * @param date        the date
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        dateAndTime = new DateAndTime(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateAndTime.formatDateToString() + ")";
    }
}