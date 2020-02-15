/**
 * DukeException class handles the exception specific to Duke chatbot.
 */
public class DukeException extends Exception {
    String errorMsg = "";

    /**
     * Constructs DukeException object in the event that the user typed something Duke could not understand.
     * @param errorMsg Error message.
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    /**
     * Prints out the exception.
     * @return String of the exception.
     */
    @Override
    public String toString() {
        return errorMsg;
    }
}
