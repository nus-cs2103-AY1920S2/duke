package duke.exception;

/**
 * Generates an error message for the situation where user input cant be decoded.
 */
public class DukeExceptionCommand extends DukeException {
    public DukeExceptionCommand() {
        super.errorMsg = "Instruction unclear. Fin stuck in microwave.";
    }
}
