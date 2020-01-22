package task;

public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getStatusIcon() {
    return (this.isDone ? "\u2713" : "\u2718"); // return tick or X symbols
  }

  public void setDone() {
    this.isDone = true;
  }

  @Override
  public String toString() {
    return String.format("%s %s", this.getStatusIcon(), description);
  }
}