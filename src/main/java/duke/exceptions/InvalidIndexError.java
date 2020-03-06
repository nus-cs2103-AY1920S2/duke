package duke.exceptions;

public class InvalidIndexError extends Exceptions {

    int listSize;

    /**
     * Constructor.
     *
     * @param listSize refers to total amount of task.
     */
    public InvalidIndexError(int listSize) {
        super(null);
        this.listSize = listSize;
    }

    /**
     * String representation of the exception.
     *
     * @return error message.
     */
    @Override
    public String toString() {

        if (listSize == 0) {

            return "There are no tasks. Add tasks before doing the command!";
        }

        assert listSize > 0 : "List is empty!";

        return "The task number should be from 1 to " + listSize;

    }
}
