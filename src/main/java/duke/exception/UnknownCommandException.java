package duke.exception;
public class UnknownCommandException extends RuntimeException {
  public UnknownCommandException(String command) {
    super("Sorry my guy, I don't know the command: '" + command + "'");
  }
}
