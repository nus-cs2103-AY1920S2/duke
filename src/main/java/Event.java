public class Event extends Task {

    protected String at;

    /**
     * Constructor for the Deadline object, a subclass of Task
     * @param description String containing the description of the task
     * @param at String setting the deadline for this task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }


    /**
     * Formats this object as a String to be printed out
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}