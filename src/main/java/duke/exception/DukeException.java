package duke.exception;

/**
 * Parent class of all the exceptions specific to the Duke Application.
 */
public class DukeException extends Exception {
    // to avoid warning because of inheriting from a Serializable class
    private static final long serialVersionUID = 1000000;

    /**
     * Constructs a DukeException object.
     * @param message The reason for generation of exception.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
