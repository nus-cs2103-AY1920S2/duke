/**
 * Deadline Class.
 * Deadline Class is a subclass of Task Class. It stores the description of this instance
 * as well as the date and time.
 *
 * @author Amos Cheong
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Get the description of this Deadline object.
     * @return String description of this Deadline object.
     */
    public String getDesc() {
        return super.description;
    }

    /**
     * Get the date and time of this Deadline object.
     * @return String date and time.
     */
    public String getDate() {
        return by;
    }

    /**
     * Get the type of this object.
     * Used when outputting to user.
     * @return String The object type.
     */
    public String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}