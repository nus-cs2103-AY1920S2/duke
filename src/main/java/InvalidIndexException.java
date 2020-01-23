public class InvalidIndexException extends RuntimeException {
    InvalidIndexException(String key) {
        super(key + " is an invalid index!");
    }
}
