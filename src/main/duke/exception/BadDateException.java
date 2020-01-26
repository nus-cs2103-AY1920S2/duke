package main.exception;

public class BadDateException extends DukeException {
    public BadDateException(String errorMsg) {
        super(errorMsg);
    }
}
