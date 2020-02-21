package duke.exceptions;

/** Exception thrown when the wrong arguments are applied to a command. */
public class IncorrectArgumentException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public IncorrectArgumentException(String message) {
        super(message);
    }
}