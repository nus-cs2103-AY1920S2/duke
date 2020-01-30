package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import duke.exception.TimeFormatException;

/** Class representing the parser with methods to parse strings. */
public class Parser {
  /**
   * Parse initial command to String array
   *
   * @param longCommand String representation of the whole command typed in by user.
   * @return String array, position 0 is the command type, position 1 is the description.
   */
  public String[] parseCommand(String longCommand) {
    return longCommand.split(" ", 2);
  }

  /**
   * @param str String of the time input of the user.
   * @return LocalDateTime representation of that time format.
   */
  public LocalDateTime stringToTime(String str) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
      return LocalDateTime.parse(str, formatter);
    } catch (DateTimeParseException e) {
      throw new TimeFormatException();
    }
  }
}
