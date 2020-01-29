/**
 * Event class that can take in an event with the place or timing.
 */
public class Event extends Task {

    private String timing;

    /**
     * Creates an Event task that holds the description and timing information.
     * @param description description of the event.
     * @param timing time or place of the event.
     */
    public Event(String description, String timing) {
        super(description);
        int newTimingFormat = timing.indexOf(" ");
        this.timing = timing.substring(0, newTimingFormat) + ":"+ timing.substring(newTimingFormat);

    }

    /**
     * Formatted to save into hard disk.
     * @return a format that is standardised to be saved into the hard disk.
     */
    @Override
    public String saveToHardDiskFormat() {

        return String.format("E | %d | %s | %s", this.completedCode, this.getDescription(), this.timing.replace("at: ", ""));
    }


    /**
     * Overrided to string method to show the type of task it is.
     * @return an extra [E] to denote that it is a Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.timing + ")";

    }
}
