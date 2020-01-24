package exception;

import java.lang.Exception;

public class DukeException extends Exception {
  public DukeException(String issue) {
    super(String.format("WARNING!! %s", issue));
  }
}