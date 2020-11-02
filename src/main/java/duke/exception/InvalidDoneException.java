package duke.exception;

/**
 * Thrown when an invalid format is given for Done command.
 */
public class InvalidDoneException extends InvalidFormatException {
    public InvalidDoneException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "\n\n" + "For done commands, follow this format: done {intended task number}";
    }
}
