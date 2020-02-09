package seedu.duke.exception;

public class InvalidDateException extends DukeException {
    @Override
    public String toString() {
        return "Date format should be in yyyy-mm-dd";
    }
}
