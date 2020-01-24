package task;

public class Todo extends Task {
  public Todo(String description) {
    super("[T]", description);
  }

  public Todo(String[] fromMemory) {
    super("[T]", fromMemory[1], fromMemory[2]);
  }
}
