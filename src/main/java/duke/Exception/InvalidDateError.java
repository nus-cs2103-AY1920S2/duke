package duke.Exception;

/**
 * @version 1.0
 * @since 2020-01-28
 */
public class InvalidDateError extends Exceptions {

    public InvalidDateError() {
        super(null);
    }

    /**
     * Constructs error message with respect the error
     * @return error message of the error
     */
    @Override
    public String errorMessage() {

        return "Date should be in d/MM/yyyy HH:mm format";

    }
}
