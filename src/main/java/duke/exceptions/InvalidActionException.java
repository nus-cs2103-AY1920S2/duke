package duke.exceptions;

/**
 * Throws InvalidActionException when the user specifies
 * an invalid action.
 *
 * @author ChesterSim
 */
public class InvalidActionException extends Exception {
    public InvalidActionException() {
        super();
    }

    @Override
    public String toString() {
        return "I'm sorry, but I don't know what that means :-(";
    }
}
