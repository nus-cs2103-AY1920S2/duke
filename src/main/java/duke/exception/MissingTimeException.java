package duke.exception;
public class MissingTimeException extends RuntimeException {
  public MissingTimeException(String type) {
    super(type + " is missing time!");
  }
}
