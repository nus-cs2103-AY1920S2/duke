package task;

import parser.TimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for the Deadline Task.
     *
     * @param name the name of the Deadline task
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = new TimeFormatter(by).parseToFormat();
    }


    /**
     * Constructor for the Deadline Task.
     *
     * @param name the name of the Deadline task
     * @param isDone the boolean value representing the status of the Deadline task
     */
    public Deadline(String name, String by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by + ")";
    }

    /**
     * Returns a summary of the Deadline task in the file format.
     *
     * @return Returns a summary of the Deadline task in the file format
     */
    public String toStringFileFormat() {
        return "D" + super.toStringFileFormat()
                + " | " + this.by;
    }
}
