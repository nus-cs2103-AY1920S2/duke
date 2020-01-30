package duke;

/** Class representation of Task. */
public class Task {

  protected String task;
  protected Boolean done = false;

  /**
   *
   * @param todo String containing what to do.
   */
  Task(String todo) {
    this.task = todo;
  }

  /**
   * Set this task as done.
   */
  public void done() {
    this.done = true;
  }

  /**
   *
   * @return String representation of the task to print.
   */
  @Override
  public String toString() {
    if (done) {
      return "[✓] " + this.task;
    } else {
      return "[✗] " + this.task;
    }
  }

  /**
   *
   * @return String representation of the task to save.
   */
  public String toSaveString() {
    if (done) {
      return "1";
    } else {
      return "0";
    }
  }
}
