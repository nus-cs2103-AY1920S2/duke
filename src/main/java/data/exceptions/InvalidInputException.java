package data.exceptions;

public abstract class InvalidInputException extends Exception{

    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
