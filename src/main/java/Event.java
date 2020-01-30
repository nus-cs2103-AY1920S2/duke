import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
  protected LocalDateTime time;

  Event(String todo, LocalDateTime time) {
    super(todo);
    this.time = time;
  }

  @Override
  public String toString() {
    String timeStr = this.time.format(DateTimeFormatter.ofPattern("HH:mm, MMM d yyyy"));
    return String.format("[E]%s (at: %s)", super.toString(), timeStr);
  }

  @Override
  public java.lang.String toSaveString() {
    String timeStr = this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    return String.format("%s || event || %s || %s", super.toSaveString(), this.task, timeStr);
  }
}
