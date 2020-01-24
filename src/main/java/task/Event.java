package task;

import exception.DukeException;
import parser.Parser;

import java.time.format.DateTimeFormatter;

public class Event extends TimeTask {
  public Event(String description) throws DukeException {
    super("[E]", description);
    this.time = Parser.getTime(description, "/at");
    this.date = Parser.getDate(description, "/at");
  }

  public Event(String[] fromMemory) {
    super("[E]", fromMemory);
  }

  @Override
  public String toString() {
    return String.format("%s (at: %s %s)", super.toString(),
        this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")), this.time);
  }
}
