package task;

import exception.DukeException;
import parser.Parser;

public class Task {
  protected String description;
  protected boolean isDone;
  protected String type;

  public Task(String type, String description) {
    this.type = type;
    this.description = description;
    this.isDone = false;
  }

  public static Task newTask(String description) throws DukeException {
    String type = Parser.getType(description);
    String typeLess = description.substring(type.length()).trim();

    switch (type) {
      case "todo":
        return new Todo(typeLess);
      case "event":
        return new Event(typeLess);
      case "deadline":
        return new Deadline(typeLess);
      default:
        throw new DukeException("Task not recognized");
    }
  }

  public String getStatusIcon() {
    return (this.isDone ? "Y" : "N"); // Couldn't view the ticks and crosses on my terminal
  }

  public void setDone() {
    this.isDone = true;
  }

  @Override
  public String toString() {
    return String.format("%s[%s] %s", this.type, this.getStatusIcon(), description);
  }
}
