package duke;

/**
 * The type Deadline.
 */
public class Deadline extends Task {

    /**
     * The Date and time.
     */
    private DateAndTime dateAndTime;

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description
     * @param date        the date
     */
    public Deadline(String description, String date) throws DukeException {
        super(description);
        /**
         * The Date.
         */
        dateAndTime = new DateAndTime(date);
    }

    /**
     * Sets a new date and time for deadlines.
     *
     * @param newInput input the new date as a string
     */
    public void setDateAndTime(String newInput) throws DukeException {
        dateAndTime = new DateAndTime(newInput);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateAndTime.formatDateToString() + ")";
    }

}