package duke.Exception;

/**
 * @version 1.0
 * @since 2020-01-28
 */
public class EmptyDateError extends Exceptions {

    public EmptyDateError(String type) {

        super(type);
    }

    /**
     * Constructs error message with respect the error
     * @return error message of the error
     */
    @Override
    public String errorMessage() {

        return "\nOPPS! The date of a " +  super.getType() + " cannot be empty.";

    }
}
