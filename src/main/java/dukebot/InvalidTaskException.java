package dukebot;

public class InvalidTaskException extends Throwable {

    /**
     * Standard error message.
     */
    public InvalidTaskException(String message) {
        super(message);
    }

    //     public InvalidTaskException(String message, Throwable throwable) {
    //        super(message, throwable);
    //    }
}
