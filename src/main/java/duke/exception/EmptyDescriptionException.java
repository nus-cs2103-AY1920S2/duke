package duke.exception;
public class EmptyDescriptionException extends RuntimeException {
  public EmptyDescriptionException(String type) {
    super("The description of " + type + " cannot be empty.");
  }
}
