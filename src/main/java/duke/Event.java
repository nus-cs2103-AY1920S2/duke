package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Class representing an Event. */
public class Event extends Task {
  protected LocalDateTime time;

  /**
   * @param todo String representing the task that needs to be done
   * @param time LocalDateTime representing the time event is at.
   */
  Event(String todo, LocalDateTime time) {
    super(todo);
    this.time = time;
  }

  /** @return String representation of the event to print. */
  @Override
  public String toString() {
    String timeStr = this.time.format(DateTimeFormatter.ofPattern("HH:mm, MMM d yyyy"));
    return String.format("[E]%s (at: %s)", super.toString(), timeStr);
  }
  /** @return String representation of the event to save. */
  @Override
  public java.lang.String toSaveString() {
    String timeStr = this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    return String.format("%s || event || %s || %s", super.toSaveString(), this.task, timeStr);
  }
}
