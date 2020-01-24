package task;

import exception.DukeException;
import parser.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
  protected LocalDate date;
  protected LocalTime time;

  public Deadline(String description) throws DukeException {
    super("[D]", Parser.getContent(description));
    this.time = Parser.getTime(description, "/by");
    this.date = Parser.getDate(description, "/by");
  }

  public Deadline(String[] fromMemory) {
    super("[D]", fromMemory[1], fromMemory[2]);
    this.date = LocalDate.parse(fromMemory[3]);
    this.time = LocalTime.parse(fromMemory[4]);
  }

  @Override
  public String toString() {
    return String.format("%s (by: %s %s)", super.toString(),
        this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")), this.time);
  }

  @Override
  public String toStorable() {
    return String.format("%s|%s|%s", super.toStorable(), this.date, this.time);
  }
}
