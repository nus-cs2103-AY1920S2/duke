package duke.exception;

/** Error to throw when the user input time format is wrong. */
public class TimeFormatException extends RuntimeException {
  public TimeFormatException() {
    super("Please enter date in the format yyyy-MM-dd HHmm");
  }
}
