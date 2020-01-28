package command;

/** Tasks without any date/time attached to it e.g., visit new theme park */
public class ToDos extends Task {
  public ToDos(String description) {
    super(description);
  }

  /**
   * @param isDone Whether the task is completed by the user
   * @param description The activity description
   */
  public ToDos(boolean isDone, String description) {
    super(description);
    this.isDone = isDone;
  }

  @Override
  public String toString() {
    return "[T]" + "[" + getStatusIcon() + "] " + this.description;
  }

  /** @return String to be stored in task text file */
  @Override
  public String fileString() {
    return "T|" + getStatusIcon() + "|" + this.description;
  }

  @Override
  public boolean containsString(String keyword) {
    return this.toString().contains(keyword);
  }
}
