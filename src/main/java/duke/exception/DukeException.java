/**
 *  Exception class for all Duke exceptions.
 */

package duke.exception;

public class DukeException extends Exception {
    /**
     * Constructor for all Duke exceptions.
     * @param message Error/Exception message to be shown to the user.
     */
    public DukeException(String message) {
        super(message);
    }
}
