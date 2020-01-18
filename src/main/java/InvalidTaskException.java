public class InvalidTaskException extends Throwable {
    public InvalidTaskException(String message) {
        super(message);
    }

    public InvalidTaskException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
