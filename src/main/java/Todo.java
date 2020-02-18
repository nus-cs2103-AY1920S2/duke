/**
 * Class Todo to represent a Todo Task. A Todo Task
 * consists of a description and a time.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo Task with {@code description}.
     *
     * @param description the description/details of our task
     */
    public Todo(String description) {
        super(description, "");
    } // time attribute is not needed in this class

    /**
     * returns a String representation of a Todo instance.
     *
     * @return String String representation
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * returns a String representation of a Todo instance in our database.
     *
     * @return String a Todo representation in our database
     */
    @Override
    public String toStringFile() {
        return "T" + " | " + super.toStringFile();
    }
}
