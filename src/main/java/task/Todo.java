package task;

public class Todo extends Task {
  public Todo(String description) {
    super("[T]", description);
  }

  public Todo(String description, String isDone) {
    super("[T]", description, isDone);
  }
}
