package task;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Task {
  protected String description;
  protected boolean isDone;
  protected String type;

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

  public static Task newTask(String description) {
    try {
      String content = description.substring(description.indexOf(" ") + 1);
      if (isTodo(description)) {
        return new Todo(content);
      } else if (isEvent(description)) {
        return new Event(content);
      } else if (isDeadline(description)) {
        return new Deadline(content);
      } else {
        throw new IllegalArgumentException("This is not a right task input");
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("This is not a right task input");
    }
  }

  public static String getTime(String description) {
    Matcher matcher = Pattern.compile("(/by|/at)").matcher(description);
    int index = matcher.find() ? matcher.start() : -1;
    return description.substring(index + 3).trim();
  }

  public static String getContent(String description) {
    Matcher matcher = Pattern.compile("(/by|/at)").matcher(description);
    int index = matcher.find() ? matcher.start() : -1;
    return description.substring(0, index).trim();
  }

  public String getStatusIcon() {
    return (this.isDone ? "\u2713" : "\u2718"); // return tick or X symbols
  }

  public void setDone() {
    this.isDone = true;
  }

  @Override
  public String toString() {
    return String.format("%s[%s] %s", this.type, this.getStatusIcon(), description);
  }
}
