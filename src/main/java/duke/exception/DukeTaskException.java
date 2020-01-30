/**
 *  Exception class for Duke exceptions related to Tasks.
 */

package duke.exception;

public class DukeTaskException extends DukeException {
    /**
     * Constructor for Task-related exceptions.
     * @param message Error/Exception message to be shown to the user.
     */
    public DukeTaskException(String message) {
        super(message);
    }
}
