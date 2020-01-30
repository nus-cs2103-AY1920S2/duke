package duke.exception;

/** Error to throw when the command is not known. */
public class UnknownCommandException extends RuntimeException {
  public UnknownCommandException(String command) {
    super("Sorry my guy, I don't know the command: '" + command + "'");
  }
}
