package duke.exceptions;

/**
 * The Exception program is an error thrown when there is a user input error.
 *
 * @version 1.1
 * @since 9/2/2020
 */
public class Exceptions extends Exception {

    protected String type;

    /**
     * Constructor.
     * @param type refers to the type of Task.
     */
    public Exceptions(String type) {

        this.type = type;

    }


    /**
     * Gets the type of task with error.
     *
     * @return type of task.
     */
    public String getType() {

        return type;

    }

    /**
     * String representation of the exception.
     *
     * @return error message.
     */
    @Override
    public String toString() {
        return this.toString();
    }
}
