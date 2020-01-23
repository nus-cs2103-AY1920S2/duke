/*
 * DukeUnknownKeywordException
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 21 Jan 2020
 *
 */

/**
 * <p>The DukeUnknownKeywordException class extends the DukeException
 * class and it handles the unknown keyword entered by the client.</p>
 * @author Mario Lorenzo
 */

public class DukeUnknownKeywordException extends DukeException {
    /**
     * Constructs a DukeInvalidArgumentFormatException class with the error
     * message.
     * @param message The error message of the DukeInvalidArgumentFormatException;
     */

    public DukeUnknownKeywordException(String message) {
        super(message);
    }
}
