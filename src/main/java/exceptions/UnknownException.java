package exceptions;

/**
 * Handles the exception if the user
 * gives an inappropriate command.
 */

public class UnknownException extends Exception{
    //private static final String SPACE = "     ";
    public UnknownException() {};

    public String toString() {
        return " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
