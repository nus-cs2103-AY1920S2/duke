package duke.exception;

/**
 * Thrown when user inputs invalid whatsup command.
 */
public class InvalidWhatsupException extends InvalidFormatException {
    public InvalidWhatsupException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "\n\n" + "For whatsup commands, follow this format: whatsup /on dd-mm-yyyy";
    }
}
