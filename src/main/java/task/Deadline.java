package task;

public class Deadline extends Task {
  protected String time;

  public Deadline(String description) {
    super("[D]", Task.getContent(description));
    this.time = Task.getTime(description);
  }

  @Override
  public String toString() {
    return String.format("%s (by: %s)", super.toString(), this.time);
  }
}
