/**
 *  Exception class for Duke exceptions related to unknown user commands.
 */

package duke.exception;

public class DukeUnknownException extends DukeException {
    /**
     * Constructor for exceptions related to unknown commands.
     * @param message Error/Exception message to be shown to the user.
     */
    public DukeUnknownException(String message) {
        super(message);
    }
}
