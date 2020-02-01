package duke;

public class DukeException extends Exception{

    /**
     * Returns exceptions for invalid Duke inputs
     * @param message String message to be displayed
     */
    public DukeException(String message) {
        super(message);
    }
}
