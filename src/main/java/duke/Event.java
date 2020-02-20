package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/** Represents an Event which inherits from Task and is stored/managed by Duke */
public class Event extends Task {
  public static final String DESCRIPTION_ERROR = "Description of Event is empty";
  public static final String REGEX_DATE = "\\d{4}-\\d{2}-\\d{2}";
  /** Stores the time the event is supposed to take place */
  protected String time;

  protected Optional<LocalDate> dueTime;
  protected boolean timePresent;

  /**
   * Creates an Event object with given description and time
   *
   * @param description
   * @param time
   */
  public Event(String description, String time) {
    super(description);
    assert description.length() > 0 : DESCRIPTION_ERROR;
    this.time = time;
    timePresent = false;
    if (this.time.matches(REGEX_DATE)) { // YYYY-MM-DD
      this.dueTime = Optional.of(LocalDate.parse(this.time));
      timePresent = true;
    }
  }

  /**
   * Creates an Event object with given description, time and done status. Used when loading data
   * from data.txt file
   *
   * @param description
   * @param time
   * @param isDone
   */
  public Event(String description, String time, boolean isDone) {
    super(description, isDone);
    assert description.length() > 0 : DESCRIPTION_ERROR;
    this.time = time;
    timePresent = false;
    if (this.time.matches(REGEX_DATE)) { // YYYY-MM-DD
      this.dueTime = Optional.of(LocalDate.parse(this.time));
      timePresent = true;
    }
  }

  /**
   * Gives a string representation of the Event by building upon parent's representation method
   *
   * @return
   */
  @Override
  public String toString() {
    if (timePresent) {
      return "[E]"
          + super.toString()
          + String.format(
              "(at: %s)", this.dueTime.get().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    } else {
      return "[E]" + super.toString() + String.format("(at: %s)", this.time);
    }
  }

  /**
   * Gives a string representation of the Event object used for storing it in the data.txt file
   *
   * @return
   */
  public String toFile() {
    return "E | " + super.toFile() + " | " + time;
  }
}
