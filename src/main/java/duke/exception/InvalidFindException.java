package duke.exception;

/**
 * Thrown when user inputs invalid Find command.
 */
public class InvalidFindException extends InvalidFormatException {
    public InvalidFindException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "\n\n" + "For find commands, follow this format: find "
                + "{(complete or partial) task or note name}";
    }
}
