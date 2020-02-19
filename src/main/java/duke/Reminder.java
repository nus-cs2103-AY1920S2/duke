package duke;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Reminder extends Task {

  LocalDateTime timing;
  String description;

  Reminder(String input) throws DukeException {
    super(input);
    this.timing = getTiming(input);
    this.description = getDescription(input);
    setReminder();
  }

  public void timedPrint(long delay) {
	  MainWindow.triggerReminder(description, delay);
  }

  /**
   * Gets timing from the input parsed in the LocalDateTime format eg: 2011-12-03T10:15:30.
   *
   * @param input from the string input user keys in.
   * @return timing as LocalData object.
   * @throws DukeException if date is the wrong format.
   */
  private LocalDateTime getTiming(String input) throws DukeException {
    try {
      String[] strArr = input.split(" ");
      int index = 0;
      for (int j = 0; j < strArr.length; j++) {
        String stringItem = strArr[j];
        if (stringItem.equals("/at")) {
          index = j;
          break;
        }
      }
      StringBuilder str = new StringBuilder();
      for (int i = index + 1; i < strArr.length; i++) {
        str.append(" ").append(strArr[i]);
      }
      String date = str.toString().trim();
      return LocalDateTime.parse(date);
    } catch (DateTimeParseException d) {
      throw new DukeException("dateTime", "");
    }
  }

  private void setReminder() throws DukeException{
		LocalDateTime currentTime = LocalDateTime.now();
		Duration duration = Duration.between(currentTime, timing);
		if (duration.getSeconds() >= 0) {
			timedPrint(duration.getSeconds());
		} else {
			throw new DukeException("exceedTime", description);
		}
  }

  /**
   * Gets description based on the input parsed.
   *
   * @param input from the string input user keys in.
   * @return description as a String.
   */
  private String getDescription(String input) {
    StringBuilder str = new StringBuilder();
    String[] strArr = input.split(" ");
    for (int i = 1; i < strArr.length; i++) {
      if (strArr[i].equals("/at")) {
        break;
      }
      str.append(strArr[i]).append(" ");
    }
    return str.toString();
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder("[R]");
    str.append(this.getStatusIcon())
        .append(" ")
        .append(description)
        .append("(at: ")
        .append(timing.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")))
        .append(")");
    return str.toString();
  }
}
