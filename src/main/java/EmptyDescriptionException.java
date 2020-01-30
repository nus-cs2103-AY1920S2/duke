public class EmptyDescriptionException extends RuntimeException {
  EmptyDescriptionException(String type) {
    super("The description of " + type + " cannot be empty.");
  }
}
