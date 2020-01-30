/**
 *  Exception class for Duke exceptions related to improper argument(s) provided.
 */
package duke.exception;

public class DukeArgumentException extends DukeException {
    /**
     * Constructor for argument-related exceptions.
     * @param message Error/Exception message to be shown to the user.
     */
    public DukeArgumentException(String message) {
        super(message);
    }
}
