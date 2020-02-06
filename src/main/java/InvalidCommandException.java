/**
 * Exception class for our application Duke. This Exception is caught
 * when the user input commands cannot be recognised by our ChatBot.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
