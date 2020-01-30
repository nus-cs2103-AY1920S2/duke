package duke.exception;

/** Exception to throw when the index given for done and delete is not valid. */
public class InvalidIndexException extends RuntimeException {
  public InvalidIndexException(String key) {
    super("'" + key + "' is an invalid index!");
  }
}
