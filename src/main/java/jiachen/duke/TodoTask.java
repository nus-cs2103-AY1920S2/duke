package jiachen.duke;

/**
 * This object represents a Todo Task. Todo tasks are generic tasks that just contain descriptions
 */
public class TodoTask extends Task {

  /**
   * Instantiates a new Todo task.
   *
   * @param description the description
   * @throws InvalidDukeFormatException the invalid duke format exception
   */
  public TodoTask(String description) throws InvalidDukeFormatException {
    super(description);
    if (description.equals("")) {
      throw new InvalidDukeFormatException("The description of a todo cannot be empty.");
    }
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

  @Override
  public String format() {
    return "T | " + super.format();
  }
}
