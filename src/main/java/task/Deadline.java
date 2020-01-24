package task;

import exception.DukeException;

public class Deadline extends Task {
  protected String time;

  public Deadline(String description) throws DukeException {
    super("[D]", Task.getContent(description));
    this.time = Task.getTime(description, "/by");
  }

  public Deadline(String description, String isDone, String time) {
    super("[D]", description, isDone);
    this.time = time;
  }

  @Override
  public String toString() {
    return String.format("%s (by: %s)", super.toString(), this.time);
  }

  @Override
  public String toStorable() {
    return String.format("%s|%s", super.toStorable(), this.time);
  }
}
