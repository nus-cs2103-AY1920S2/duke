/**
 * Represent an unique exception thrown by my Duke program.
 */
public class DukeException extends Exception {
    /**
     * Constructor of unique exception thrown.
     *
     * @param errorType unique error message.
     */
    public DukeException(String errorType) {
        super(errorType);
    }
}
