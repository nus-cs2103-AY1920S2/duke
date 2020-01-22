package task;

import exception.DukeException;

public class Deadline extends Task {
  protected String time;

  public Deadline(String description) throws DukeException {
    super("[D]", Task.getContent(description));
    this.time = Task.getTime(description, "/by");
  }

  @Override
  public String toString() {
    return String.format("%s (by: %s)", super.toString(), this.time);
  }
}
