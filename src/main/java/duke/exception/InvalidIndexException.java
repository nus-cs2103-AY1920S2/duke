package duke.exception;

/**
 * InvalidIndexException
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class InvalidIndexException extends DukeException {
    int size;
    int num;

    /**
     * Constructs an InvalidIndexException.
     * @param num The index number of the task to handle.
     * @param size The size of the current list of tasks.
     */
    public InvalidIndexException(int num, int size) {
        this.size = size;
        this.num = num;
    }

    /**
     * Overrides the Object's toString method
     * and contains details of the error.
     * @return The String that containing reason for the error and how to resolve it.
     */
    @Override
    public String toString() {
        String back = "You currently have " + size + (size < 2 ? " task." : " tasks.") + "\nPlease enter a number in the range 1 to " + size;
        if (num < 1) {
            return "Index supplied is too small. " + back;
        }
        return "Index supplied is too large. " + back;
    }
}
