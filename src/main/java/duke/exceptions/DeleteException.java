package duke.exceptions;

public class DeleteException extends DukeException {

    /**
     * Constructor for DeleteException
     *
     * @param message any message to convey
     */
    public DeleteException(String message) {
        super(message);
    }

    public String toString() {
        return ExceptionsConstant.FORMAT_LINE
                + "\nDeleting Tasks require a valid task number and the task needs to be previously entered\n"
                + ExceptionsConstant.FORMAT_LINE;
    }
}
