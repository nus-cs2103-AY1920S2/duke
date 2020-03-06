package duke.exceptions;

/**
 * The DuplicateTask program is an error thrown when user inputs
 * an already existing task.
 *
 * @version 1.2
 * @since 19/2/2020
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

        if (super.type.equals("T")) {

            return "The todo task already exists!";

        } else if (super.type.equals("D")) {

            return "The deadline task already exists!";

        }

        assert super.type.equals("E") : "Wrong task type!";

        return "The event task already exists!";

    }

}
