package duke.exception;

/**
 * Thrown when an invalid format is given for Delete command.
 */
public class InvalidDeleteException extends InvalidFormatException {
    public InvalidDeleteException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "\n\n" + "For delete commands, follow this format: delete {intended task number}";
    }
}
