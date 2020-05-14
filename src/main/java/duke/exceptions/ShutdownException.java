package duke.exceptions;

/** Exception that is thrown when 'bye'command is given. */
public class ShutdownException extends Exception {
   
    private static final long serialVersionUID = 1L;

    public ShutdownException(String message) {
        super("Shutting down now. " + message);
    }
}