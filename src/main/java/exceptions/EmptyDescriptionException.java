package exceptions;

/**
 * Throws EmptyDescriptionException when user does
 * not specify a description after an action.
 *
 * @author ChesterSim
 */
public class EmptyDescriptionException extends Exception {
    private String taskType;

    public EmptyDescriptionException(String taskType) {
        super();
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        String taskType;

        return ":( OOPS!!! The description of the " + this.taskType + " cannot be empty.";
    }
}
