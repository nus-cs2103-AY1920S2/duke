package exception;

/**
 * Represents a custom Error caused by Illegal Text entered by the User
 */
public class IllegalTextException extends Exception {

    /**
     * Constructor for the IllegalTextException object.
     * Calls the parent constructor of Exception class.
     *
     * @param errorMessage Error Message to describe the illegal user input.
     */
    public IllegalTextException(String errorMessage) {
        super(errorMessage);
    }
}
