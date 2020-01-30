import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
  public String[] parseCommand(String longCommand) {
    return longCommand.split(" ", 2);
  }

  public LocalDateTime stringToTime(String str) {
    try {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    return LocalDateTime.parse(str, formatter);
    } catch (DateTimeParseException e){
      throw new TimeFormatException();
    }
  }
}
