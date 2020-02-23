package duke;

/**
 * Class for handling empty list.
 */
public class EmptyListException extends DukeException {
    /**
     * toString method.
     * @return the toString method
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
