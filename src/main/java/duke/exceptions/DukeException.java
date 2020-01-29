package duke.exceptions;

public class DukeException extends Exception {

    /**
     * Constructor for DukeException, parent exception of all exceptions in duke.Duke program
     *
     * @param message any message to convey
     */
    public DukeException(String message) {
        super(message);
    }

}
