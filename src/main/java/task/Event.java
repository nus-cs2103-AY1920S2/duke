package task;

import exception.DukeException;

public class Event extends Task {
  protected String time;

  public Event(String description) throws DukeException {
    super("[E]", Task.getContent(description));
    this.time = Task.getTime(description, "/at");
  }

  @Override
  public String toString() {
    return String.format("%s (at: %s)", super.toString(), this.time);
  }
}
