public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
        assert message != null : "No error message to print out";
    }
}
