package task;

import exception.DukeException;
import parser.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
  protected LocalDate date;
  protected LocalTime time;

  public Event(String description) throws DukeException {
    super("[E]", Parser.getContent(description));
    this.time = Parser.getTime(description, "/at");
    this.date = Parser.getDate(description, "/at");
  }

  @Override
  public String toString() {
    return String.format("%s (by: %s %s)", super.toString(),
        this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")), this.time);
  }
}
