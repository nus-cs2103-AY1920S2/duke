/*
 * @author leslieharland
 */

package duke;

/**
 * The Class DukeException.
 */
public class DukeException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new duke exception.
     *
     * @param message the message
     */
    public DukeException(String message) {
        super(message);
    }

}
