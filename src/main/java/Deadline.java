/**
 * Deadline Class.
 * Deadline Class is a subclass of Task Class. It stores the description of this instance
 * as well as the date and time. The only difference from the Event task is that it
 * make known to the user of what needs to be done before the date.
 *
 * @author Amos Cheong
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDesc() {
        return super.description;
    }

    public String getDate() {
        return by;
    }

    public String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}