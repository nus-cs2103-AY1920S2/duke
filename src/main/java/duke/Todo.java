package duke;

/**
 * Class representing todo task.
 */
public class Todo extends Task {
  /**
   *
   * @param todo String containing what to do.
   */
  Todo(String todo) {
    super(todo);
  }

  /**
   *
   * @return String representation of the todo task to print.
   */
  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

  /**
   *
   * @return String representation of the todo task to save.
   */
  @Override
  public java.lang.String toSaveString() {
    return String.format("%s || todo || %s", super.toSaveString(), this.task);
  }
}
