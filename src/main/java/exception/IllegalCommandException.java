package exception;

/**
 * Represents a custom Error caused by Illegal Commands entered by the User
 */
public class IllegalCommandException extends Exception {

    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
