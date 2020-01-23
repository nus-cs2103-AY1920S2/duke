public class Event extends Task {
    private String eventDate;

    /**
     * Event constructor.
     *
     * @param eventArgs Joined string of the user input command arguements
     */
    public Event(String eventArgs) {
        super(eventArgs.split(" /at ")[0]);
        this.eventDate = eventArgs.split(" /at ")[1];
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