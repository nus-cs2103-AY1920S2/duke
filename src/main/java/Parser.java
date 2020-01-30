import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
  public String[] parseCommand(String longCommand) {
    return longCommand.split(" ", 2);
  }

  public LocalDateTime stringToTime(String str) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    return LocalDateTime.parse(str, formatter);
  }
}
