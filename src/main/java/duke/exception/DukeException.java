package duke.exception;

public class DukeException extends Exception {
    public static String exceptionIcon = "\u2639"; // Sad face symbol

    /**
     * Constructs a new exception related to duke.Duke class
     *
     * @param message to represent more information about exception
     */
    public DukeException(String message) {
        super(message);
    }
}
