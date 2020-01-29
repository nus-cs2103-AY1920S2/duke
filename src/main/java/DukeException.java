public class DukeException extends RuntimeException {
    String message;

    public DukeException(String message) {
        super();
        this.message = message;
    }
}