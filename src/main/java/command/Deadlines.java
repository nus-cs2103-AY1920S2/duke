package command;

/** Tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm */
public class Deadlines extends Task {
  protected String datetime = "";

  public Deadlines(String description, String datetime) {
    super(description);
    this.datetime = datetime;
  }

  /**
   * @param isDone Whether the task is completed by the user
   * @param description The activity description
   * @param datetime The date and time described in /by command
   */
  public Deadlines(boolean isDone, String description, String datetime) {
    super(description);
    this.isDone = isDone;
    this.datetime = datetime;
  }

  @Override
  public String toString() {
    return "[D]" + "[" + getStatusIcon() + "] " + this.description + " (by: " + this.datetime + ")";
  }

  /** @return String to be stored in task text file */
  @Override
  public String fileString() {
    return "D|" + getStatusIcon() + "|" + this.description + "|" + this.datetime;
  }

  @Override
  public boolean containsString(String keyword) {
    return this.toString().contains(keyword) || datetime.contains(keyword);
  }
}
