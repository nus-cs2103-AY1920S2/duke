package app.exceptions;

public class WrongCommandException extends BaseException {
    public WrongCommandException(String message) {
        super(message);
    }
}