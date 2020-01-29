public class DukeException extends RuntimeException {
    protected String message;

    public DukeException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}