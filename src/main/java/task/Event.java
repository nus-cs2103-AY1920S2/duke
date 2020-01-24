package task;

import exception.DukeException;
import parser.Parser;

import java.time.format.DateTimeFormatter;

public class Event extends TimeTask {
  public Event(String description) throws DukeException {
    super(Constant.EVENT.getType(), description);
    this.time = Parser.getTime(description, Constant.EVENT.getTimeDelimiter());
    this.date = Parser.getDate(description, Constant.EVENT.getTimeDelimiter());
  }

  public Event(String[] fromMemory) {
    super(Constant.EVENT.getType(), fromMemory);
  }

  @Override
  public String toString() {
    return String.format("%s (at: %s %s)", super.toString(),
        this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")), this.time);
  }
}
