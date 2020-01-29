public class Deadline extends Task {
  protected String time;

  Deadline(String todo, String time) {
    super(todo);
    this.time = time;
  }

  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), time);
  }

  @Override
  public java.lang.String toSaveString() {
    return String.format("%s || deadline || %s || %s", super.toSaveString(), this.task, this.time);
  }
}
