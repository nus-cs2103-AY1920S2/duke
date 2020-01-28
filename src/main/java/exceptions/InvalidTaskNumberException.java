package exceptions;

/**
 * Throws InvalidTaskNumberException when user inputs a task number
 * out of range.
 *
 * @author ChesterSim
 */
public class InvalidTaskNumberException extends Exception{
    private int numberOfTasks;

    public InvalidTaskNumberException(int numberOfTasks) {
        super();
        this.numberOfTasks = numberOfTasks;
    }

    @Override
    public String toString() {
        return "Please input a valid task number between 1 - " + numberOfTasks;
    }
}
