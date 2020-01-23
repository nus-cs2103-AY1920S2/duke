public class Event extends Task {
    private String eventDate;

    /**
     * Event constructor.
     *
     * @param eventArgs Joined string of the user input command arguements
     * @throws DukeException Incorrect format/missing date
     */
    public Event(String eventArgs) throws DukeException {
        super(eventArgs.split(" /at ")[0]);
        try {
            this.eventDate = eventArgs.split(" /at ")[1];
        } catch (Exception e) {
            throw new DukeException(2);
        }
    }

    /**
     * A method to return the details of the current Task object.
     * Contains the task done status and the task name.
     *
     * @return A String representation of the task details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
            + this.eventDate + ")";
    }
}