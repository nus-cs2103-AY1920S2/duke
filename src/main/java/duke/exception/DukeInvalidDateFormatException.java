package duke.exception;

/*
 * DukeInvalidDateFormatException
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 27 Jan 2020
 *
 */

/**
 * <p>DukeInvalidDateFormatException class extends the DukeException
 * and it handles the invalid format of the date of event and deadline</p>
 * @author Mario Lorenzo
 */

public class DukeInvalidDateFormatException extends DukeException {

    /**
     * Constructs a DukeInvalidDateFormatException with an
     * error message.
     * @param message The corresponding error message.
     */

    public DukeInvalidDateFormatException(String message) {
        super(message);
    }

    /**
     * Overrides the toString method.
     * @return The error message.
     */

    @Override
    public String toString() {
        return getMessage();
    }
}
