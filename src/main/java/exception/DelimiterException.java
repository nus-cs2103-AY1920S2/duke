package exception;

public class DelimiterException extends UIException {
    public DelimiterException(String delimiter) {
        super(String.format("Dear user, your input is missing this %s delimiter", delimiter));
    }
}
