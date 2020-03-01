/**
 * CS2103 Individual Project.
 * The Event class extends the Task class, it encapsulates all the information pertaining
 * to event objects, and contains a deadline which is a String.
 * @author Chiang Shun De
 */

public class Event extends Task {
    private String deadline;

    /**
     * The constructor class for the Event class.
     * @param description The string description of the Event.
     * @param deadline The deadline date and or time of the Event.
     */
    public Event(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Saves the event object in a human readable string format with all
     * the important information
     * @return A String containing information of the event saved in a
     * human readable format.
     */
    @Override
    public String saveTask(){
        return "E | " + this.binary + " | " + this.description + " | " + this.deadline;
    }

    /**
     * Returns a String representation of an Event object
     * @return A String representation of an Event object
     */
    @Override
    public String toString() {
        return "     [E]" + this.getIcon() + " " + this.description + "(at:" + this.deadline + ")";
    }
}
