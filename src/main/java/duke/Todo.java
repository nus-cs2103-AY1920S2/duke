package duke;
public class Todo extends Task {
  Todo(String todo) {
    super(todo);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

  @Override
  public java.lang.String toSaveString() {
    return String.format("%s || todo || %s", super.toSaveString(), this.task);
  }
}
