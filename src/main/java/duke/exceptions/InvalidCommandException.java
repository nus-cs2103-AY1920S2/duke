package duke.exceptions;

/** Exception that is thrown when an invalid command is given. */
public class InvalidCommandException extends Exception {
   
    private static final long serialVersionUID = 1L;

    public InvalidCommandException(String message) {
        super(message);
    }
}