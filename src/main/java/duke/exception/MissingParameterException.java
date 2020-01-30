package duke.exception;

/** Exception to throw when the params of commands are missing. */
public class MissingParameterException extends RuntimeException {
  public MissingParameterException(String type) {
    super("Missing parameters for " + type );
  }
}
