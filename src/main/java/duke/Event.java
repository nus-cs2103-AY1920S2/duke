package duke;

/**
 * The type Event.
 */
public class Event extends Task {

    /**
     * The Date and time.
     */
    private DateAndTime dateAndTime;

    /**
     * Instantiates a new Event.
     *
     * @param description the description
     * @param date        the date
     */
    public Event(String description, String date) {
        super(description);
        /**
         * The Date.
         */
        dateAndTime = new DateAndTime(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateAndTime.formatDateToString() + ")";
    }
}