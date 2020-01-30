package duke.exception;

public class TimeFormatException extends RuntimeException {
  public TimeFormatException() {
    super("Please enter date in the format yyyy-MM-dd HHmm");
  }
}
