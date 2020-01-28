package command;

public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {

    this.description = description;
  }

  public void markAsDone() {
    isDone = true;
  }

  public String getStatusIcon() {
    return (isDone ? "✓" : "✕"); // return tick or X symbols
  }

  public String fileString() {
    return "D|" + getStatusIcon() + "|" + description;
  }
}
