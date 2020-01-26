package main.java.duke.exception;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException (String errorMsg) {
        super(errorMsg);
    }
}
