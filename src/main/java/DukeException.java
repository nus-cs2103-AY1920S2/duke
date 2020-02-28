//package duke;

/**
 * Represents exceptions that may happen while running Duke.
 */
public class DukeException extends Exception {
    private String type;

    /**
     * Creates a new DukeException with the given type.
     */
    public DukeException (String type) {
        this.type = type;
    }

    /**
     * Gets the type of a DukeException.
     * @return the type of this exception.
     */
    public String getType() {
        return this.type;
    }
}