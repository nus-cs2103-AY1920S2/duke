package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Class representing a Deadline */
public class Deadline extends Task {
  /** Time that task must be completed by. */
  protected LocalDateTime time;

  /**
   * @param todo String containing what to do.
   * @param time LocalDateTime object representing time that task must be completed by.
   */
  Deadline(String todo, LocalDateTime time) {
    super(todo);
    this.time = time;
  }

  /** @return String representation of the deadline to print. */
  @Override
  public String toString() {
    String timeStr = this.time.format(DateTimeFormatter.ofPattern("HH:mm, MMM d yyyy"));
    return String.format("[D]%s (by: %s)", super.toString(), timeStr);
  }

  /** @return String representation of the deadline to save. */
  @Override
  public String toSaveString() {
    String timeStr = this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    return String.format("%s || deadline || %s || %s", super.toSaveString(), this.task, timeStr);
  }
}
