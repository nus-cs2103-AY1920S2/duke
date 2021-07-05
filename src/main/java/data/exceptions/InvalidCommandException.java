package data.exceptions;

public class InvalidCommandException extends InvalidInputException {

    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
