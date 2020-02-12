package duke.exception;

/**
 * Thrown when an error occur due to invalid number format.
 */
public class DukeNumberFormatException extends DukeException {

    /**
     * Constructs a DukeNumberFormatException for a specified command.
     *
     * @param command The specified command.
     */
    public DukeNumberFormatException(String command) {
        super(String.format(Messages.MESSAGE_INVALID_NUMBER_FORMAT, command));
    }
}
