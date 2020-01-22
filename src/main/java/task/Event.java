package task;

public class Event extends Task {
  protected String time;

  public Event(String description) {
    super("[E]", Task.getContent(description));
    this.time = Task.getTime(description);
  }

  @Override
  public String toString() {
    return String.format("%s (at: %s)", super.toString(), this.time);
  }
}
