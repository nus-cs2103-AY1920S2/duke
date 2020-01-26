package main.duke.exception;

public class BadDescriptionException extends DukeException {
    public BadDescriptionException (String errorMsg) {
        super(errorMsg);
    }
}
