package duke.Exception;

/**
 * @version 1.0
 * @since 2020-01-28
 */
public class Exceptions extends Exception {

    protected String type;

    public Exceptions(String type) {

        this.type = type;

    }

    /**
     * Constructs error message with respect the error
     * @return error message of the error
     */
    public String errorMessage() {

        return this.errorMessage();

    }

    /**
     * Gets the type of task with error
     * @return type of task
     */
    public String getType() {

        return type;

    }

}
