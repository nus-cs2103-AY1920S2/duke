public class MissingTimeException extends RuntimeException {
  MissingTimeException(String type) {
    super(type + " is missing time!");
  }
}
