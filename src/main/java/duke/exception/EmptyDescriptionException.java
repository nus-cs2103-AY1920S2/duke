package duke.exception;

/** Exception to throw when the description of event and deadline is empty. */
public class EmptyDescriptionException extends RuntimeException {
  public EmptyDescriptionException(String type) {
    super("The description of " + type + " cannot be empty.");
  }
}
