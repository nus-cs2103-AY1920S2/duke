import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Event is a sub-class of Task. Represents a task on a certain date. */
public class Event extends Task {

  String by;
  String strDoneStatus;
  LocalDate date;

  /**
   * Constructor that takes in 2 params.
   *
   * @param event Name of the event.
   * @param by Date of the event.
   */
  public Event(String event, String by) {
    super(event);
    this.by = by;
    this.date = LocalDate.parse(by);
  }

  /**
   * Constructor that takes in 3 params.
   *
   * @param event Name of the event.
   * @param by Date of the event.
   * @param i Defines the done status of the event. Reads either 1 (complete) or 0 (incomplete).
   */
  public Event(String event, String by, String strDoneStatus) {
    super(event);
    this.strDoneStatus = strDoneStatus;
    this.by = by;
    if (strDoneStatus.equals("1")) {
      this.doneStatus = true;
    } else {
      this.doneStatus = false;
    }
    this.date = LocalDate.parse(by);
  }

  /**
   * Returns the format to be saved in the output txt file.
   *
   * @return Returns format in as a string.
   */
  @Override
  public String save() {
    int myInt = doneStatus ? 1 : 0;
    return "E , " + myInt + " , " + taskName + " , " + by;
  }

  @Override
  public String toString() {
    String formattedBy = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    return "[E]" + super.toString() + " (by: " + formattedBy + ")";
  }
}
