package duke.exceptions;

/**
 * The DuplicateTask program is an error thrown when user inputs
 * an already existing task.
 *
 *
 */
public class DuplicateTaskError extends Exceptions {

    /**
     * Constructor.
     *
     * @param type refers to the type of Task.
     */
    public DuplicateTaskError(String type) {

        super(type);
    }

    @Override
    public String toString() {

        return "OPPS! The task already exists!";
    }
}
