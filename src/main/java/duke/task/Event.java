package duke.task;

/**
 * Represents event task.
 */
public class Event extends Task {
    String by;

    public Event(String description, String time) {
        super(description);
        this.by = time;
    }

    /**
     * Returns event task in string format with a date.
     * @return string format to save in storage.
     */
    @Override
    public String saveFormat() {
        return "E" + super.saveFormat() + "," + by;
    }

    /**
     * Returns event task in string format.
     * @return string format to show user.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }
}
