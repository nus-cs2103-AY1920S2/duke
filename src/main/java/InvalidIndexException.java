public class InvalidIndexException extends DukeException {
    @Override
    public String getMessage() {
        String msg = "The index you have entered is invalid.";
        return msg;
    }
}
