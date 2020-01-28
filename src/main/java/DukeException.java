public class DukeException extends Exception {
    public DukeException(String error, Throwable err) {
        super(error, err);
    }

    public DukeException(String error) {
        super(error);
    }
}
