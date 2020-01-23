/*
 * DukeInvalidArgumentFormatException
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 21 Jan 2020
 *
 */

/**
 * <p>The DukeInvalidArgumentFormatException class extends the DukeException
 * class and it handles the invalid format entered by the client.</p>
 * @author Mario Lorenzo
 */

public class DukeInvalidArgumentFormatException extends DukeException {
    /**
     * Constructs a DukeInvalidArgumentFormatException class with the error
     * message.
     * @param message The error message of the DukeInvalidArgumentFormatException;
     */

    public DukeInvalidArgumentFormatException(String message) {
        super(message);
    }
}
