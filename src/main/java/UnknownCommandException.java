public class UnknownCommandException extends RuntimeException {
  UnknownCommandException(String command) {
    super("Sorry my guy, I don't know the command: '" + command + "'");
  }
}
