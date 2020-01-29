package exceptions;

public class WrongDateTimeFormatException extends Exception {
    public WrongDateTimeFormatException(String message) {
        super(message);
    }
}