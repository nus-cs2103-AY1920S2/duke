package duke.exception;
public class InvalidIndexException extends RuntimeException {
  public InvalidIndexException(String key) {
    super(key + " is an invalid index!");
  }
}
