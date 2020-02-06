/**
 * Exception class for our application Duke. This Exception is caught
 * when the user input commands have missing parameters (MissingArgumentException),
 * or the user input commands cannot be found in our ChatBot (InvalidCommandException)
 */
public class DukeException extends IllegalArgumentException {
    public DukeException(String message) {
        super(message);
    }
}
