package task;

import exception.DukeException;
import parser.Parser;

import java.time.format.DateTimeFormatter;

public class Deadline extends TimeTask {
  public Deadline(String description) throws DukeException {
    super("[D]", description);
    this.time = Parser.getTime(description, "/by");
    this.date = Parser.getDate(description, "/by");
  }

  public Deadline(String[] fromMemory) {
    super("[D]", fromMemory);
  }

  @Override
  public String toString() {
    return String.format("%s (by: %s %s)", super.toString(),
        this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")), this.time);
  }
}
