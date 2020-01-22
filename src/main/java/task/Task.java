package task;

import exception.DukeException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Task {
  protected String description;
  protected boolean isDone;
  protected String type;
  private static String[] taskTypes = {"todo", "event", "deadline"};

  public Task(String type, String description) {
    this.type = type;
    this.description = description;
    this.isDone = false;
  }

  private static Boolean isTodo(String description) {
    return Pattern.matches("^todo .*", description);
  }

  private static Boolean isEvent(String description) {
    return Pattern.matches("^event .* /at .*", description);
  }

  private static Boolean isDeadline(String description) {
    return Pattern.matches("^deadline .* /by .*", description);
  }

  public static Boolean isTask(String description) {
    return Task.isTodo(description) || Task.isEvent(description) || Task.isDeadline(description);
  }

  private static String getType(String words) {
    for (String type : Task.taskTypes) {
      if (words.contains(type))
        return type;
    }
    return "";
  }

  public static Task newTask(String description) throws DukeException {
    if (Task.getType(description) == "")
      throw new DukeException("Task not recognized");

    String type = Task.getType(description.split(" ")[0]);
    if (type == "") // if type exists, but is not at the front
      throw new DukeException("Please start with event type");

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

  public static String getTime(String description, String regex) throws DukeException {
    Matcher matcher = Pattern.compile(regex).matcher(description);
    int index = matcher.find() ? matcher.start() : -1;
    if (index == -1) {
      throw new DukeException(String.format("Please provide %s for this event type", regex));
    }
    String time = description.substring(index + regex.length()).trim();
    if (time.length() == 0)
      throw new DukeException("Please provide a time");
    return time;
  }

  public static String getContent(String description) throws DukeException {
    Matcher matcher = Pattern.compile("(/by|/at)").matcher(description);
    int index = matcher.find() ? matcher.start() : -1;
    if (index == -1 && description.trim().length() > 0) {
      return description.trim();
    }
    String content = description.substring(0, index).trim();
    if (content.length() > 0)
      return content;
    throw new DukeException("Content cannot be empty!");
  }

  public String getStatusIcon() {
    return (this.isDone ? "Y" : "N"); // return tick or X symbols
  }

  public void setDone() {
    this.isDone = true;
  }

  @Override
  public String toString() {
    return String.format("%s[%s] %s", this.type, this.getStatusIcon(), description);
  }
}
