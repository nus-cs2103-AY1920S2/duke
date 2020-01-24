package task;

import exception.DukeException;
import parser.Parser;

import java.time.format.DateTimeFormatter;

public class Deadline extends TimeTask {

  public Deadline(String description) throws DukeException {
    super(Constant.DEADLINE.getType(), description);
    this.time = Parser.getTime(description, Constant.DEADLINE.getTimeDelimiter());
    this.date = Parser.getDate(description, Constant.DEADLINE.getTimeDelimiter());
  }

  public Deadline(String[] fromMemory) {
    super(Constant.DEADLINE.getType(), fromMemory);
  }

  @Override
  public String toString() {
    return String.format("%s (by: %s %s)", super.toString(),
        this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")), this.time);
  }
}
