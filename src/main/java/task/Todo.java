package task;

public class Todo extends Task {

	public Todo(String description) {
		super(description);
	}

	/**
	 * This method formats the Todo object based on its representation into a format suitable for
	 * writing to a file.
	 *
	 * @return A String object that can be easily written and retrieved from the data file.
	 */
	public String format() {
		return "T" + " | " + (this.isDone ? "1" : "0") + " | " + description;
	}

	/**
	 * A specialised toString() method based on implementations of the object.
	 *
	 * @return A specialised Todo representation of the Event object.
	 */
	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}
