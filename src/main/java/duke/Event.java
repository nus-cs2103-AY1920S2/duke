package duke;

/**
 * The type Event.
 */
public class Event extends Task {

    /**
     * The Date and time.
     */
    DateAndTime dateAndTime;
    /**
     * The Date.
     */
    protected String date;

    /**
     * Instantiates a new Event.
     *
     * @param description the description
     * @param date        the date
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
        dateAndTime = new DateAndTime(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateAndTime.formatDateToString() + ")";
    }
}