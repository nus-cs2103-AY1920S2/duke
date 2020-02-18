package duke;

public class InvalidIndexException extends DukeException {
    @Override
    public String getErrorMessage() {
        return "I couldn't understand which index that was. Please input a valid integer.";
    }
}
