package duke.exception;

/**
 * The base class for all exceptions specific to Duke
 */
public class DukeException extends Exception{
    public final String description;

    /**
     * Constructs a DukeException with a description of what went wrong
     *
     * @param description description or details of the exception
     */
    public DukeException (String description){
        this.description = description;
    }
}
