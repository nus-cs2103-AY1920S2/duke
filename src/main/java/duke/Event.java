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
    public Event(String description, String date) throws DukeException {
        super(description);
        /**
         * The Date.
         */
        dateAndTime = new DateAndTime(date);
    }

    /**
     * Sets a new date and time for events.
     *
     * @param newInput input the new date as a string
     */
    public void setDateAndTime(String newInput) throws DukeException {
        dateAndTime = new DateAndTime(newInput);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateAndTime.formatDateToString() + ")";
    }
}