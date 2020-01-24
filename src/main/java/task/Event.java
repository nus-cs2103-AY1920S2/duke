package task;

import exception.DukeException;

public class Event extends Task {
  protected String time;

  public Event(String description) throws DukeException {
    super("[E]", Task.getContent(description));
    this.time = Task.getTime(description, "/at");
  }

  public Event(String[] fromMemory) {
    super("[E]", fromMemory[1], fromMemory[2]);
    this.time = fromMemory[3];
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
