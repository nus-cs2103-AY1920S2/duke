package duke.exceptions;

/**
 * Handles unexpected input.
 */
public class DukeUnknownCommandException extends DukeException {
    public DukeUnknownCommandException() {
        super.message = "What the hell are you saying?? I don't get you...";
    }
}
