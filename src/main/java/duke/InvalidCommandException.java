package duke;

/**
 * Thrown to indacate that a command is not valid.
 */
class InvalidCommandException extends DukeException {
    /**
     * Constructs an <code>InvalidCommandException</code> with the specified detail message.
     *
     * @param message the detail message
     */
    InvalidCommandException(String message) {
        super(message);
    }
}
