package exception;

public class DateTimeException extends UIException {
    public DateTimeException(String acceptedFormat) {
        super(
                String.format(
                        "your %s is in the wrong format",
                        acceptedFormat));
    }
}
