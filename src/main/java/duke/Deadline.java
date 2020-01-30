package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

  protected LocalDateTime time;

  Deadline(String todo, LocalDateTime time) {
    super(todo);
    this.time = time;
  }

  @Override
  public String toString() {
    String timeStr = this.time.format(DateTimeFormatter.ofPattern("HH:mm, MMM d yyyy"));
    return String.format("[D]%s (by: %s)", super.toString(), timeStr);
  }

  @Override
  public String toSaveString() {
    String timeStr = this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    return String.format("%s || deadline || %s || %s", super.toSaveString(), this.task, timeStr);
  }
}
