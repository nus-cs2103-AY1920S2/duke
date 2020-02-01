package duke.exception;

/*
 * DukeException
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 21 Jan 2020
 *
 */

/**
 * <p>The DukeException class extends the Exception class and
 * it handles the input error by the client, such as lack
 * of arguments, as well as invalid type of arguments entered
 * by the client.</p>
 * @author Mario Lorenzo
 */

public class DukeException extends Exception {

    /**
     * Constructs a DukeException instance.
     * @param message The error message.
     */

    public DukeException(String message) {
        super(message);
    }

    /**
     * Overrides the toString method to show the error message.
     * @return The error message.
     */

    @Override
    public String toString() {
        return getMessage();
    }
}
