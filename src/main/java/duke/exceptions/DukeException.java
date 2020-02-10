/**
 * DukeException handles all kinds of exceptions that arise from Duke
 */

package duke.exceptions;

public class DukeException extends Exception {
    public DukeException(String error) {
        super(error);
    }
}