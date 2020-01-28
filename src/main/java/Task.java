/** Superclass of the Event, Deadline and Todos class. */
public class Task {
  public boolean doneStatus;
  public String taskName;

  /**
   * Constructor. Initialises the task name and the done status as incomplete.
   *
   * @param taskName Name of the task.
   */
  public Task(String taskName) {
    this.taskName = taskName;
    this.doneStatus = false;
  }

  /** Marks the task as complete / incomplete. */
  public void mark() {
    this.doneStatus = !this.doneStatus;
  }

  /**
   * The save format of each task.
   *
   * @return Returns null as it is overwritten in the subclasses.
   */
  public String save() {
    return null;
  }

  public String getTaskName() {
    return this.taskName;
  }

  public boolean getDoneStatus() {
    return this.doneStatus;
  }

  @Override
  public String toString() {
    String symbol;
    if (doneStatus) {
      symbol = "  complete  ";
    } else {
      symbol = " incomplete ";
    }
    return "[" + symbol + "] " + taskName;
  }
}
