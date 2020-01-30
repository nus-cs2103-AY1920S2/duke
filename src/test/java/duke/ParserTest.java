package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
  @Test
  public void stringToTimeTest() {
    Parser parser = new Parser();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    assertEquals(parser.stringToTime("1998-03-15 1530"), LocalDateTime.parse("1998-03-15 1530", formatter));
  }
}
