/**
 * Represents an exception when an invalid index is accessed in creating a Task.
 */
public class InvalidTaskDateTimeException extends ArrayIndexOutOfBoundsException {
    InvalidTaskDateTimeException(String message) {
        super(message);
    }
}