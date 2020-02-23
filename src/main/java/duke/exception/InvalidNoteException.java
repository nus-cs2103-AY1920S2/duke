package duke.exception;

/**
 * Thrown when user inputs invalid Note command.
 */
public class InvalidNoteException extends InvalidFormatException {
    public InvalidNoteException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "\n\n" + "For note commands, follow this format: note {intended note}";
    }
}