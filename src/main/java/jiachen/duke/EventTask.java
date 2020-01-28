package jiachen.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event task takes in at at time clause
 */
public class EventTask extends Task {

  private LocalDateTime at;

  /**
   * Instantiates a new Event task.
   *
   * @param description the description
   * @param at          the at
   * @throws InvalidDukeFormatException the invalid duke format exception
   * @throws DateTimeParseException     the date time parse exception
   */
  public EventTask(String description, String at)
          throws InvalidDukeFormatException, DateTimeParseException {
    super(description);

    if (at.isEmpty()) {
      throw new InvalidDukeFormatException("Missing /at clause or missing at when!");
    }

    this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d YYYY");
    return "[E]" + super.toString() + " (by: " + formatter.format(this.at) + ")";
  }

  @Override
  public String format() {
    return "E | "
            + super.format()
            + " | "
            + DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm").format(this.at);
  }
}
