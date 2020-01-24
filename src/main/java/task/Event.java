package task;

import exception.DukeException;

public class Event extends Task {
  protected String time;

  public Event(String description) throws DukeException {
    super("[E]", Task.getContent(description));
    this.time = Task.getTime(description, "/at");
  }

  public Event(String description, String isDone, String time) {
    super("[E]", description, isDone);
    this.time = time;
  }

  @Override
  public String toString() {
    return String.format("%s (at: %s)", super.toString(), this.time);
  }

  @Override
  public String toStorable() {
    return String.format("%s|%s", super.toStorable(), this.time);
  }
}
